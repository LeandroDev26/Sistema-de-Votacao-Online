/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 package View;

import Interfaces.IObservadorVoto;
import Model.Enquete;
import Model.RegistroDeVotos;

public class Resultado implements IObservadorVoto {
    
   private RegistroDeVotos registro;

    public Resultado(RegistroDeVotos registro) {
        this.registro = registro;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[ALERTA EM TEMPO REAL]: " + mensagem);
    }
    
    public void mostrarRelatorioGeral() {
        System.out.println("\n=== RELATÓRIO FINAL ===");
        for (Enquete e : registro.listarTodas()) {
            System.out.println("Enquete " + e.getId() + ": " + e.getPergunta());
            if(e.getResultados().isEmpty()) {
                System.out.println("  (Sem votos ainda)");
            } else {
                e.getResultados().forEach((opcao, qtd) -> 
                    System.out.println("  - " + opcao + ": " + qtd + " votos")
                );
            }
        }
        System.out.println("=======================");
    }
}
   

