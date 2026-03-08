# 🗳️ Online Voting System: Padrões de Projeto

Este projeto foi desenvolvido como o **Trabalho Final** da disciplina de Programação Orientada a Objetos II (4º Período). Trata-se de uma plataforma para criação de enquetes e registro de votos, estruturada para garantir segurança e escalabilidade.

## 🎯 Objetivo do Projeto
Demonstrar a aplicação de padrões de projeto para resolver problemas de autenticação, controle de acesso e atualização de dados em um sistema de votação simulado.

## 🏗️ Padrões de Projeto Aplicados
Seguindo os requisitos do Trabalho Final, o sistema utiliza:

* **Singleton (Criacional):** Aplicado na classe `RegistroDeVotos`, garantindo que exista apenas uma instância responsável por centralizar a contagem de votos em todo o sistema.
* **Proxy (Estrutural):** Utilizado como uma camada de segurança antes do registro do voto. O Proxy verifica se o usuário está autenticado ou se já votou antes de permitir o acesso ao objeto real de votação.
* **Observer (Comportamental):** Implementado para atualizar o painel de resultados. Sempre que um novo voto é computado, os "observadores" são notificados automaticamente.



## 🛠️ Requisitos e Arquitetura
* **Persistência:** Os votos e enquetes são salvos em Banco de Dados (PostgreSQL), garantindo que os dados não sejam perdidos ao reiniciar.
* **Camadas (MVC):** Separação clara entre as entidades de dados (Model), a lógica de validação (Controller) e a exibição dos resultados (View).
* **Segurança:** Controle básico para evitar votos duplicados e acessos não autorizados.


## 💻 Como executar
1. Clone o repositório.
2. Compile o código:
   ```bash
   javac Main.java