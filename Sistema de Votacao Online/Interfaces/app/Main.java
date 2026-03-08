/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package app;

import Interfaces.IRegistroDeVoto;
import Controller.ProxyProtecao;
import Model.Enquete;
import Model.RegistroDeVotos;
import View.Resultado;
import java.util.Scanner;
import Model.RegistroEmBd;

/**
 *
 * @author LEAND
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RegistroDeVotos gerenciadorEnquetes = RegistroDeVotos.getInstance();

        RegistroEmBd repositorioBd = new RegistroEmBd();

        try {
            System.out.println("Carregando enquetes do banco de dados...");
            gerenciadorEnquetes.inicializarDadosDoBanco();
        } catch (Exception e) {
            System.out.println(">> Aviso: Não foi possível carregar do banco (Verifique se o método existe ou se o banco está ligado).");
        }
        // ---------------------------------------------

        ProxyProtecao sistema = new ProxyProtecao(repositorioBd);

        Resultado tela = new Resultado(gerenciadorEnquetes);
        gerenciadorEnquetes.notificador.inscrever(tela);

        while (true) {
            System.out.println("\n=== SISTEMA DE VOTAÇÃO (PERSISTENTE) ===");
            System.out.println("1. Criar Nova Enquete");
            System.out.println("2. Votar");
            System.out.println("3. Ver Resultados");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            int opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMenu) {
                
                   case 1: System.out.print("Digite a Pergunta da Enquete: ");
                    String pergunta = scanner.nextLine();

                    Enquete nova = gerenciadorEnquetes.criarEnquete(pergunta);

                    System.out.print("Quantas opções de resposta? ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < qtd; i++) {
                        System.out.print("Digite a opção " + (i + 1) + ": ");
                        nova.adicionarOpcao(scanner.nextLine());
                    }

                    repositorioBd.salvarNovaEnquete(nova);

                    System.out.println("Sucesso! Enquete criada e salva no Banco com ID: " + nova.getId());
                    break;

                case 2:
                    System.out.println("\n--- Enquetes Disponíveis ---");
                    if (gerenciadorEnquetes.listarTodas().isEmpty()) {
                        System.out.println("(Nenhuma enquete cadastrada)");
                    } else {
                        for (Enquete e : gerenciadorEnquetes.listarTodas()) {
                            System.out.println("ID " + e.getId() + " - " + e.getPergunta() + " " + e.getResultados().keySet());
                        }

                        System.out.print("Digite o ID da Enquete: ");
                        int idVoto = scanner.nextInt();
                        scanner.nextLine();

                        if (gerenciadorEnquetes.buscarEnquete(idVoto) != null) {
                            System.out.print("Seu Nome: ");
                            String user = scanner.nextLine();

                            System.out.print("Sua Escolha: ");
                            String op = scanner.nextLine();

                            
                            sistema.votar(user, idVoto, op);
                        } else {
                            System.out.println("ID inválido!");
                        }
                    }
                    break;

                case 3:
                    gerenciadorEnquetes.inicializarDadosDoBanco();
                    // ---------------------------------------------------------------

                    tela.mostrarRelatorioGeral();
                    break;

                case 4:
                    System.out.println("Encerrando e salvando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
