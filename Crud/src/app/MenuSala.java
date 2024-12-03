package app;

import Classes.Sala;
import java.util.Scanner;
import java.util.ArrayList;
import API.Generic;

public class MenuSala {
  private Scanner scanner = new Scanner(System.in);
  private Sala sala = new Sala(0, 0, "", "");

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarSala();
          break;
        case "2":
          listarSalas();
          break;
        case "3":
          consultarSala();
          break;
        case "4":
          editarSala();
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
    System.out.println("Gerenciamento de Salas");
    System.out.println("1 - Cadastrar Sala");
    System.out.println("2 - Listar Salas");
    System.out.println("3 - Consultar Sala");
    System.out.println("4 - Editar Sala");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarSala() {
    System.out.println("Cadastro de Sala:");
    System.out.print("Digite o ID da sala: ");
    int idSala = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite a capacidade da sala: ");
    int capacidade = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite a descrição da sala: ");
    String descricao = scanner.nextLine();
    System.out.print("Digite o status da sala: (ATIVO/INATIVO) ");
    String status = scanner.nextLine();

    Sala novaSala = new Sala(idSala, capacidade, descricao, status);
    if (novaSala.cadastrar()) {
      System.out.println("Sala cadastrada com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar a sala. Verifique se o ID já existe.");
    }
  }

  private void listarSalas() {
    System.out.println("Lista de Salas:");
    ArrayList<Sala> salas = sala.listar();

    if (salas != null && !salas.isEmpty()) {
      for (Sala s : salas) {
        System.out.println(s);
      }
    } else {
      System.out.println("Nenhuma sala encontrada.");
    }
  }

  private void consultarSala() {
    System.out.print("Digite o ID da sala que deseja consultar: ");
    String idSala = scanner.nextLine();

    Sala salaConsultada = sala.consultar(idSala);
    if (salaConsultada != null) {
      System.out.println("Sala encontrada:");
      System.out.println(salaConsultada);
    } else {
      System.out.println("Sala não encontrada.");
    }
  }

  private void editarSala() {
    System.out.print("Digite o ID da sala que deseja editar: ");
    String idSala = scanner.nextLine();

    Sala salaEditar = sala.consultar(idSala);
    if (salaEditar != null) {
      System.out.println("Sala atual:");
      System.out.println(salaEditar);

      System.out.print("Digite a nova capacidade da sala: ");
      salaEditar.setCapacidadeSala(Integer.parseInt(scanner.nextLine()));
      System.out.print("Digite a nova descrição da sala: ");
      salaEditar.setDescricao(scanner.nextLine());
      System.out.print("Digite o novo status da sala: ");
      salaEditar.setStatus(scanner.nextLine());

      if (salaEditar.editar()) {
        System.out.println("Sala editada com sucesso!");
      } else {
        System.out.println("Erro ao editar a sala.");
      }
    } else {
      System.out.println("Sala não encontrada.");
    }
  }
}
