/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


/**
 *
 * @author LEAND
 */

import java.util.List;

public interface IRegistroDeVoto {

    void salvarVoto(String usuario, int idEnquete, String opcao);
    boolean jaVotou(String usuario, int idEnquete);
}
