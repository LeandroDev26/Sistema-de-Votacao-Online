package Model;

import Interfaces.IRegistroDeVoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroDeVotos implements IRegistroDeVoto {
    
    private static RegistroDeVotos instance;
    
    public final GerenciadorNotificacao notificador;
    
    private Map<Integer, Enquete> enquetes;
    private List<String> logVotos;
    private int contadorId = 1;

    private RegistroDeVotos( ) {
        this.enquetes = new HashMap<>();
        this.logVotos = new ArrayList<>();
        this.notificador = new GerenciadorNotificacao();
    }
    
    public static synchronized RegistroDeVotos getInstance(){
        if (instance == null){
           instance = new RegistroDeVotos();
        }
        return instance;
    }
    
    public Enquete criarEnquete(String pergunta) {
        Enquete nova = new Enquete(contadorId++, pergunta);
        enquetes.put(nova.getId(), nova);
        return nova;
    }
    
    public Enquete buscarEnquete(int id) {
        return enquetes.get(id);
    }
    
    public List<Enquete> listarTodas() {
        return new ArrayList<>(enquetes.values());
    }

    @Override
    public void salvarVoto(String usuario, int idEnquete, String opcao){
        Enquete e = enquetes.get(idEnquete);
        
        e.contabilizarVoto(opcao);
        
        logVotos.add(usuario + "_" + idEnquete);
        
        notificador.notificarTodos("Voto de " + usuario + " na opção '" + opcao + "' (Enquete: " + e.getPergunta() + ")");
    }
    
    @Override
    public boolean jaVotou(String usuario, int idEnquete){
        return logVotos.contains(usuario + "_" + idEnquete);
   }
    
  public void inicializarDadosDoBanco() {
    RegistroEmBd banco = new RegistroEmBd();
    List<Enquete> enquetesDoBanco = banco.carregarTodasEnquetes();
    
    for (Enquete e : enquetesDoBanco) {

        this.enquetes.put(e.getId(), e);
        
        if (e.getId() >= this.contadorId) {
            this.contadorId = e.getId() + 1;
        }
    }
    System.out.println("[MEMORIA] " + enquetesDoBanco.size() + " enquetes carregadas do banco.");
}
  
}