package app;

import java.util.Scanner;
import app.MenuAtor;
import app.MenuFuncionario;
import app.MenuFilme;
import app.MenuSala;
import app.MenuIngresso;
import app.MenuSessao;

public class MenuApplication {

  public void iniciarMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Selecione o que deseja gerenciar: ");
      String escolha = scanner.nextLine();
      System.out.println("Você digitou: " + escolha); // Log para verificar entrada
      switch (escolha) {
        case "1":
          gerenciarAtores(scanner);
          break;
        case "2":
          gerenciarFuncionarios(scanner);
          break;
        case "3":
          gerenciarFilmes(scanner);
          break;
        case "4":
          gerenciarGeneros(scanner);
          break;
        case "5":
          gerenciarSessoes(scanner);
          break;
        case "6":
          gerenciarSalas(scanner);
          break;
        case "7":
          gerenciarIngressos(scanner);
          break;
        case "0":
          System.out.println("Saindo da aplicação...");
          running = false;
          break;
        default:
          System.out.println("Opção inválida! Tente novamente.");
      }
    }

    scanner.close();
  }

  private void exibirMenu() {
    System.out.println("************* MENU *************");
    System.out.println("----------------------------------------");
    System.out.println("Seja Bem Vindo ao Menu de Gerencia do Easy Movies!");
    System.out.println("----------------------------------------");
    System.out.println("1 - Atores            5 - Sessão");
    System.out.println("2 - Funcionários      6 - Sala");
    System.out.println("3 - Filme             7 - Ingressos");
    System.out.println("4 - Gênero            0 - Sair");
    System.out.println("----------------------------------------");
  }

  private void gerenciarAtores(Scanner scanner) {
    MenuAtor menuAtor = new MenuAtor();
    menuAtor.iniciarMenu();
  }

  private void gerenciarFuncionarios(Scanner scanner) {
    MenuFuncionario menuFuncionario = new MenuFuncionario();
    menuFuncionario.iniciarMenu();
  }

  private void gerenciarFilmes(Scanner scanner) {
    MenuFilme menuFilme = new MenuFilme();
    menuFilme.iniciarMenu();
  }

  private void gerenciarGeneros(Scanner scanner) {
    MenuGenero menuGenero = new MenuGenero();
    menuGenero.iniciarMenu();
  }

  private void gerenciarSessoes(Scanner scanner) {
    MenuSessao menuSessao = new MenuSessao();
    menuSessao.iniciarMenu();
  }

  private void gerenciarSalas(Scanner scanner) {
    MenuSala menuSala = new MenuSala();
    menuSala.iniciarMenu();
    ;
  }

  private void gerenciarIngressos(Scanner scanner) {
    MenuIngresso menuIngresso = new MenuIngresso();
    menuIngresso.iniciarMenu();
  }
}
