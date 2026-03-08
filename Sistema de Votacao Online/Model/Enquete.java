/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEAND
 *
 */
import java.util.HashMap;
import java.util.Map;

public class Enquete {

    private int id;
    private String pergunta;
    private Map<String, Integer> opcoes;

    public Enquete(int id, String pergunta) {
        this.id = id;
        this.pergunta = pergunta;
        this.opcoes = new HashMap<>();
    }

    public void adicionarOpcao(String opcao) {
        opcoes.put(opcao, 0);
    }

    public void contabilizarVoto(String opcao) {
        if (opcoes.containsKey(opcao)) {
            opcoes.put(opcao, opcoes.get(opcao) + 1);
        }
    }

    public boolean opcaoExiste(String opcao) {
        return opcoes.containsKey(opcao);
    }

    public void definirTotalVotos(String opcao, int total) {
        if (opcoes.containsKey(opcao)) {
            opcoes.put(opcao, total);
        }
    }

    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Map<String, Integer> getResultados() {
        return opcoes;
    }
}
