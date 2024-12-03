package app;

import Classes.Sessao;
import Classes.Filme;
import Classes.Sala;
import Classes.Funcionario;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import API.Generic;

public class MenuSessao {
  private Scanner scanner = new Scanner(System.in);
  private Sessao sessao = new Sessao(0, null, null, null, null, ""); // Instância para acessar os métodos.

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarSessao();
          break;
        case "2":
          listarSessoes();
          break;
        case "3":
          consultarSessao();
          break;
        case "4":
          editarSessao();
          break;
        case "0":
          System.out.println("Voltando ao menu principal...");
          running = false;
          break;
        default:
          System.out.println("Opção inválida! Tente novamente.");
      }
    }
  }

  private void exibirMenu() {
    System.out.println("----------------------------------------");
    System.out.println("Gerenciamento de Sessões");
    System.out.println("1 - Cadastrar Sessão");
    System.out.println("2 - Listar Sessões");
    System.out.println("3 - Consultar Sessão");
    System.out.println("4 - Editar Sessão");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarSessao() {
    System.out.println("Cadastro de Sessão:");
    System.out.print("Digite o ID da sessão: ");
    int idSessao = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite a data e hora da sessão (dd/MM/yyyy HH:mm): ");
    String dataHora = scanner.nextLine();
    System.out.print("Digite o ID do filme: ");
    int idFilme = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o ID da sala: ");
    int idSala = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite a matrícula do funcionário: ");
    int idFuncionario = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o status da sessão: (ATIVA/INATIVA)");
    String status = scanner.nextLine();

    LocalDateTime dataHoraSessao = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    Filme filme = new Filme(idFilme, "Título Filme", 12, null, "Ativo");
    Sala sala = new Sala(idSala, 100, "Descrição Sala", "Ativa");
    Funcionario funcionario = new Funcionario(idFuncionario, "12345678900", "João Silva", null, "joao@email.com");

    Sessao novaSessao = new Sessao(idSessao, dataHoraSessao, filme, sala, funcionario, status);
    if (novaSessao.cadastrar(novaSessao)) {
      System.out.println("Sessão cadastrada com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar a sessão. Verifique se o ID já existe.");
    }
  }

  private void listarSessoes() {
    System.out.println("Lista de Sessões:");
    ArrayList<Sessao> sessoes = sessao.listar();

    if (sessoes != null && !sessoes.isEmpty()) {
      for (Sessao s : sessoes) {
        System.out.println(s);
      }
    } else {
      System.out.println("Nenhuma sessão encontrada.");
    }
  }

  private void consultarSessao() {
    System.out.print("Digite o ID da sessão que deseja consultar: ");
    String idSessao = scanner.nextLine();

    Sessao sessaoConsultada = sessao.consultar(idSessao);
    if (sessaoConsultada != null) {
      System.out.println("Sessão encontrada:");
      System.out.println(sessaoConsultada);
    } else {
      System.out.println("Sessão não encontrada.");
    }
  }

  private void editarSessao() {
    System.out.print("Digite o ID da sessão que deseja editar: ");
    String idSessao = scanner.nextLine();

    Sessao sessaoEditar = sessao.consultar(idSessao);
    if (sessaoEditar != null) {
      System.out.println("Sessão atual:");
      System.out.println(sessaoEditar);

      System.out.print("Digite a nova data e hora da sessão (dd/MM/yyyy HH:mm): ");
      sessaoEditar
          .setDataHoraSessao(LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
      System.out.print("Digite o novo ID do filme: ");
      int idFilme = Integer.parseInt(scanner.nextLine());
      Filme filme = new Filme(idFilme, "Novo Título", 12, null, "Ativo");
      sessaoEditar.setFilme(filme);
      System.out.print("Digite o novo ID da sala: ");
      int idSala = Integer.parseInt(scanner.nextLine());
      Sala sala = new Sala(idSala, 120, "Nova Descrição Sala", "Ativa");
      sessaoEditar.setSala(sala);
      System.out.print("Digite a nova matrícula do funcionário: ");
      int idFuncionario = Integer.parseInt(scanner.nextLine());

      System.out.print("Digite o novo status da sessão: ");
      sessaoEditar.setStatus(scanner.nextLine());

      if (sessaoEditar.editar(sessaoEditar)) {
      System.out.println("Sessão editada com sucesso!");
      } else {
      System.out.println("Erro ao editar a sessão.");
      }
      } else {
      System.out.println("Sessão não encontrada.");
      }
    }
  }
