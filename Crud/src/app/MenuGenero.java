package app;

import Classes.Genero;
import java.util.ArrayList;
import java.util.Scanner;
import API.Generic;

public class MenuGenero {
  private Scanner scanner = new Scanner(System.in);
  private Genero genero = new Genero(0, "", "");

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarGenero();
          break;
        case "2":
          listarGeneros();
          break;
        case "3":
          consultarGenero();
          break;
        case "4":
          editarGenero();
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
    System.out.println("Gerenciamento de Gêneros");
    System.out.println("1 - Cadastrar Gênero");
    System.out.println("2 - Listar Gêneros");
    System.out.println("3 - Consultar Gênero");
    System.out.println("4 - Editar Gênero");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarGenero() {
    System.out.println("Cadastro de Gênero:");
    System.out.print("Digite o ID do gênero: ");
    int idGenero = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite a descrição do gênero: ");
    String descricao = scanner.nextLine();
    System.out.print("Digite o status do gênero (Ativo/Inativo): ");
    String status = scanner.nextLine();

    Genero novoGenero = new Genero(idGenero, descricao, status);
    if (novoGenero.cadastrar()) {
      System.out.println("Gênero cadastrado com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar o gênero. Verifique se o ID já existe.");
    }
  }

  private void listarGeneros() {
    System.out.println("Lista de Gêneros:");
    ArrayList<Genero> generos = genero.listar();

    if (generos != null && !generos.isEmpty()) {
      for (Genero g : generos) {
        System.out.println(g);
      }
    } else {
      System.out.println("Nenhum gênero encontrado.");
    }
  }

  private void consultarGenero() {
    System.out.print("Digite o ID do gênero que deseja consultar: ");
    String idGenero = scanner.nextLine();

    Genero generoConsultado = genero.consultar(idGenero);
    if (generoConsultado != null) {
      System.out.println("Gênero encontrado:");
      System.out.println(generoConsultado);
    } else {
      System.out.println("Gênero não encontrado.");
    }
  }

  private void editarGenero() {
    System.out.print("Digite o ID do gênero que deseja editar: ");
    String idGenero = scanner.nextLine();

    Genero generoEditar = genero.consultar(idGenero);
    if (generoEditar != null) {
      System.out.println("Gênero atual:");
      System.out.println(generoEditar);

      System.out.print("Digite a nova descrição do gênero: ");
      generoEditar.setDescricao(scanner.nextLine());
      System.out.print("Digite o novo status do gênero (Ativo/Inativo): ");
      generoEditar.setStatus(scanner.nextLine());

      if (generoEditar.editar()) {
        System.out.println("Gênero editado com sucesso!");
      } else {
        System.out.println("Erro ao editar o gênero.");
      }
    } else {
      System.out.println("Gênero não encontrado.");
    }
  }
}
