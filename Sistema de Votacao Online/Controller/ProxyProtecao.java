/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interfaces.IVotacao;
import Interfaces.IRegistroDeVoto;
import Model.RegistroDeVotos;


/**
 *
 * @author LEAND
 */


public class ProxyProtecao implements IVotacao {

    private VotacaoReal real;
    private IRegistroDeVoto repositorio;

    public ProxyProtecao(IRegistroDeVoto repositorio) {
        this.repositorio = repositorio;
        this.real = new VotacaoReal(repositorio);
    }

    @Override
    public void votar(String usuario, int idEnquete, String opcao) {

        if (RegistroDeVotos.getInstance().buscarEnquete(idEnquete) == null) {
             System.out.println("Enquete não existe.");
             return;
        }

               if (repositorio.jaVotou(usuario, idEnquete)) {
            System.out.println("BLOQUEADO: Usuário já votou (Verificado via DB)!");
        } else {
            real.votar(usuario, idEnquete, opcao);
        }
    }
}