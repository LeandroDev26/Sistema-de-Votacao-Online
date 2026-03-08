package Model;

import Interfaces.IRegistroDeVoto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmBd implements IRegistroDeVoto {

    // Configuração do Banco de Dados
    private static final String URL = "jdbc:postgresql://localhost:5432/votacaobd";
    private static final String USER = "postgres";
    private static final String PASS = "12345678"; 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    
    @Override
    public void salvarVoto(String usuario, int idEnquete, String opcao) {
        String sql = "INSERT INTO votos (usuario, id_enquete, opcao_escolhida) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setInt(2, idEnquete);
            stmt.setString(3, opcao);
            
            stmt.executeUpdate();
            System.out.println("[BD] Voto salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    @Override
    public boolean jaVotou(String usuario, int idEnquete) {
        String sql = "SELECT 1 FROM votos WHERE usuario = ? AND id_enquete = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setInt(2, idEnquete);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar duplicidade: " + e.getMessage());
            return false;
        }
    }

 
    
    public void salvarNovaEnquete(Enquete enquete) {
        String sqlEnquete = "INSERT INTO enquetes (pergunta) VALUES (?)";
        String sqlOpcao = "INSERT INTO opcoes (id_enquete, texto_opcao) VALUES (?, ?)";

        try (Connection conn = conectar()) {
            // Pede para retornar o ID gerado
            PreparedStatement stmt = conn.prepareStatement(sqlEnquete, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, enquete.getPergunta());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);

                
                PreparedStatement stmtOp = conn.prepareStatement(sqlOpcao);
                for (String textoOpcao : enquete.getResultados().keySet()) {
                    stmtOp.setInt(1, idGerado);
                    stmtOp.setString(2, textoOpcao);
                    stmtOp.executeUpdate();
                }
                System.out.println("[BD] Enquete salva com ID: " + idGerado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<Enquete> carregarTodasEnquetes() {
        List<Enquete> lista = new ArrayList<>();
        
        String sqlEnquetes = "SELECT * FROM enquetes ORDER BY id";
        String sqlOpcoes = "SELECT * FROM opcoes WHERE id_enquete = ?";

        String sqlContagem = "SELECT opcao_escolhida, COUNT(*) as total FROM votos WHERE id_enquete = ? GROUP BY opcao_escolhida";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlEnquetes);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String pergunta = rs.getString("pergunta");
                Enquete e = new Enquete(id, pergunta);

               
                try (PreparedStatement stmtOp = conn.prepareStatement(sqlOpcoes)) {
                    stmtOp.setInt(1, id);
                    try (ResultSet rsOp = stmtOp.executeQuery()) {
                        while(rsOp.next()){
                            e.adicionarOpcao(rsOp.getString("texto_opcao"));
                        }
                    }
                }

                try (PreparedStatement stmtVoto = conn.prepareStatement(sqlContagem)) {
                    stmtVoto.setInt(1, id);
                    try (ResultSet rsVoto = stmtVoto.executeQuery()) {
                        while(rsVoto.next()){
                            String opcao = rsVoto.getString("opcao_escolhida");
                            int total = rsVoto.getInt("total");
                            
                            
                            e.definirTotalVotos(opcao, total);
                        }
                    }
                }
                
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}