package app;

import Classes.Ingresso;
import Classes.Sessao;
import Classes.SalaAssento;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuIngresso {
  private Scanner scanner = new Scanner(System.in);
  private Ingresso ingresso = new Ingresso(0, 0.0, null, null);

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarIngresso();
          break;
        case "2":
          listarIngressos();
          break;
        case "3":
          consultarIngresso();
          break;
        case "4":
          editarIngresso();
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
    System.out.println("Gerenciamento de Ingressos");
    System.out.println("1 - Cadastrar Ingresso");
    System.out.println("2 - Listar Ingressos");
    System.out.println("3 - Consultar Ingresso");
    System.out.println("4 - Editar Ingresso");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarIngresso() {
    System.out.println("Cadastro de Ingresso:");
    System.out.print("Digite o ID do ingresso: ");
    int idIngresso = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o valor pago pelo ingresso: ");
    double valorPago = Double.parseDouble(scanner.nextLine());
    System.out.print("Digite o ID do SalaAssento: ");
    int idSalaAssento = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o ID da Sessão: ");
    int idSessao = Integer.parseInt(scanner.nextLine());

    SalaAssento salaAssento = new SalaAssento(idSalaAssento, null, null);

    Sessao sessao = new Sessao(idSessao, null, null, null, null, "Ativa");
    sessao = sessao.consultar(String.valueOf(idSessao));

    if (sessao == null) {
      System.out.println("Erro: Sessão não encontrada. Verifique o ID.");
      return;
    }

    Ingresso novoIngresso = new Ingresso(idIngresso, valorPago, salaAssento, sessao);

    if (novoIngresso.cadastrar()) {
      System.out.println("Ingresso cadastrado com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar o ingresso. Verifique se o ID já existe.");
    }
  }

  private void listarIngressos() {
    System.out.println("Lista de Ingressos:");
    ArrayList<Ingresso> ingressos = ingresso.listar();

    if (ingressos != null && !ingressos.isEmpty()) {
      for (Ingresso i : ingressos) {
        System.out.println(i);
      }
    } else {
      System.out.println("Nenhum ingresso encontrado.");
    }
  }

  private void consultarIngresso() {
    System.out.print("Digite o ID do ingresso que deseja consultar: ");
    String idIngresso = scanner.nextLine();

    Ingresso ingressoConsultado = ingresso.consultar(idIngresso);
    if (ingressoConsultado != null) {
      System.out.println("Ingresso encontrado:");
      System.out.println(ingressoConsultado);
    } else {
      System.out.println("Ingresso não encontrado.");
    }
  }

  private void editarIngresso() {
    System.out.print("Digite o ID do ingresso que deseja editar: ");
    String idIngresso = scanner.nextLine();

    Ingresso ingressoEditar = ingresso.consultar(idIngresso);
    if (ingressoEditar != null) {
      System.out.println("Ingresso atual:");
      System.out.println(ingressoEditar);

      System.out.print("Digite o novo valor pago pelo ingresso: ");
      ingressoEditar.setValorPago(Double.parseDouble(scanner.nextLine()));
      System.out.print("Digite o novo ID do SalaAssento: ");
      int idSalaAssento = Integer.parseInt(scanner.nextLine());
      System.out.print("Digite o novo ID da Sessão: ");
      int idSessao = Integer.parseInt(scanner.nextLine());

      SalaAssento salaAssento = new SalaAssento(idSalaAssento, null, null);
      ingressoEditar.setSalaAssento(salaAssento);

      Sessao sessao = new Sessao(idSessao, null, null, null, null, "Ativa");
      sessao = sessao.consultar(String.valueOf(idSessao));
      if (sessao == null) {
        System.out.println("Erro: Sessão não encontrada. Verifique o ID.");
        return;
      }
      ingressoEditar.setSessao(sessao);

      if (ingressoEditar.editar(ingressoEditar)) {
        System.out.println("Ingresso editado com sucesso!");
      } else {
        System.out.println("Erro ao editar o ingresso.");
      }
    } else {
      System.out.println("Ingresso não encontrado.");
    }
  }
}
