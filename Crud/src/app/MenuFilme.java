package app;

import Classes.Filme;
import Classes.Genero;
import java.util.ArrayList;
import java.util.Scanner;
import API.Generic;

public class MenuFilme {
  private Scanner scanner = new Scanner(System.in);
  private Filme filme = new Filme(0, "", 0, null, "");

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarFilme();
          break;
        case "2":
          listarFilmes();
          break;
        case "3":
          consultarFilme();
          break;
        case "4":
          editarFilme();
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
    System.out.println("Gerenciamento de Filmes");
    System.out.println("1 - Cadastrar Filme");
    System.out.println("2 - Listar Filmes");
    System.out.println("3 - Consultar Filme");
    System.out.println("4 - Editar Filme");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarFilme() {
    System.out.println("Cadastro de Filme:");
    System.out.print("Digite o ID do filme: ");
    int idFilme = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o título do filme: ");
    String titulo = scanner.nextLine();
    System.out.print("Digite a classificação do filme: ");
    int classificacao = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o ID do gênero do filme: ");
    int idGenero = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o status do filme: ");
    String status = scanner.nextLine();

    Genero genero = new Genero(idGenero, "Gênero Padrão", "Ativo");
    Filme novoFilme = new Filme(idFilme, titulo, classificacao, genero, status);

    if (novoFilme.cadastrar()) {
      System.out.println("Filme cadastrado com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar o filme. Verifique se o ID já existe.");
    }
  }

  private void listarFilmes() {
    System.out.println("Lista de Filmes:");
    ArrayList<Filme> filmes = filme.listar();

    if (filmes != null && !filmes.isEmpty()) {
      for (Filme f : filmes) {
        System.out.println(f);
      }
    } else {
      System.out.println("Nenhum filme encontrado.");
    }
  }

  private void consultarFilme() {
    System.out.print("Digite o ID do filme que deseja consultar: ");
    String idFilme = scanner.nextLine();

    Filme filmeConsultado = filme.consultar(idFilme);
    if (filmeConsultado != null) {
      System.out.println("Filme encontrado:");
      System.out.println(filmeConsultado);
    } else {
      System.out.println("Filme não encontrado.");
    }
  }

  private void editarFilme() {
    System.out.print("Digite o ID do filme que deseja editar: ");
    String idFilme = scanner.nextLine();

    Filme filmeEditar = filme.consultar(idFilme);
    if (filmeEditar != null) {
      System.out.println("Filme atual:");
      System.out.println(filmeEditar);

      System.out.print("Digite o novo título do filme: ");
      filmeEditar.setTitulo(scanner.nextLine());
      System.out.print("Digite a nova classificação do filme: ");
      filmeEditar.setClassificacao(Integer.parseInt(scanner.nextLine()));
      System.out.print("Digite o novo ID do gênero: ");
      int idGenero = Integer.parseInt(scanner.nextLine());
      Genero genero = new Genero(idGenero, "Gênero Atualizado", "Ativo");
      filmeEditar.setGenero(genero);
      System.out.print("Digite o novo status do filme: ");
      filmeEditar.setStatus(scanner.nextLine());

      if (filmeEditar.editar()) {
        System.out.println("Filme editado com sucesso!");
      } else {
        System.out.println("Erro ao editar o filme.");
      }
    } else {
      System.out.println("Filme não encontrado.");
    }
  }
}
