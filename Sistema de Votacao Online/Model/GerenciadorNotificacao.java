/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import Interfaces.IObservadorVoto;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorNotificacao {
    
    private List<IObservadorVoto> observadores = new ArrayList<>();

    public void inscrever(IObservadorVoto observador) {
        observadores.add(observador);
    }

    public void notificarTodos(String mensagem) {
        for (IObservadorVoto obs : observadores) {
            obs.atualizar(mensagem);
        }
    }
}
