/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interfaces.IVotacao;
import Model.RegistroDeVotos;
import Interfaces.IRegistroDeVoto;

/**
 *
 * @author LEAND
 */


public class VotacaoReal implements IVotacao {
    
    private IRegistroDeVoto repositorio;

    public VotacaoReal(IRegistroDeVoto repositorio) {
        
        this.repositorio = repositorio;
    }

    @Override
    public void votar(String usuario, int idEnquete, String opcao) {
        repositorio.salvarVoto(usuario, idEnquete, opcao);
    }
}

    

