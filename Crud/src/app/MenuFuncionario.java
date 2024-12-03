package app;

import Classes.Funcionario;
import java.util.Scanner;
import java.util.ArrayList;
import API.Generic;

public class MenuFuncionario {
  private Scanner scanner = new Scanner(System.in);
  private Funcionario funcionario = new Funcionario("");

  public void iniciarMenu() {
    boolean running = true;

    while (running) {
      exibirMenu();
      System.out.print("Escolha uma opção: ");
      String escolha = scanner.nextLine();

      switch (escolha) {
        case "1":
          cadastrarFuncionario();
          break;
        case "2":
          listarFuncionarios();
          break;
        case "3":
          consultarFuncionario();
          break;
        case "4":
          editarFuncionario();
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
    System.out.println("Gerenciamento de Funcionários");
    System.out.println("1 - Cadastrar Funcionário");
    System.out.println("2 - Listar Funcionários");
    System.out.println("3 - Consultar Funcionário");
    System.out.println("4 - Editar Funcionário");
    System.out.println("0 - Voltar");
    System.out.println("----------------------------------------");
  }

  private void cadastrarFuncionario() {
    System.out.println("Cadastro de Funcionário:");
    System.out.print("Digite a matrícula: ");
    int matricula = Integer.parseInt(scanner.nextLine());
    System.out.print("Digite o CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Digite o nome: ");
    String nome = scanner.nextLine();
    System.out.print("Digite o horário de trabalho (HH:mm): ");
    String horario = scanner.nextLine();
    System.out.print("Digite o email: ");
    String email = scanner.nextLine();

    Funcionario novoFuncionario = new Funcionario(matricula, cpf, nome, java.time.LocalTime.parse(horario), email);
    if (novoFuncionario.cadastrar()) {
      System.out.println("Funcionário cadastrado com sucesso!");
    } else {
      System.out.println("Erro ao cadastrar o funcionário. Verifique se a matrícula já existe.");
    }
  }

  private void listarFuncionarios() {
    System.out.println("Lista de Funcionários:");
    ArrayList<Funcionario> funcionarios = funcionario.listar();

    if (funcionarios != null && !funcionarios.isEmpty()) {
      for (Funcionario func : funcionarios) {
        System.out.println(func);
      }
    } else {
      System.out.println("Nenhum funcionário encontrado.");
    }
  }

  private void consultarFuncionario() {
    System.out.print("Digite a matrícula do funcionário que deseja consultar: ");
    String matricula = scanner.nextLine();

    Funcionario funcConsultado = funcionario.consultar(matricula);
    if (funcConsultado != null) {
      System.out.println("Funcionário encontrado:");
      System.out.println(funcConsultado);
    } else {
      System.out.println("Funcionário não encontrado.");
    }
  }

  private void editarFuncionario() {
    System.out.print("Digite a matrícula do funcionário que deseja editar: ");
    String matricula = scanner.nextLine();

    Funcionario funcEditar = funcionario.consultar(matricula);
    if (funcEditar != null) {
      System.out.println("Funcionário atual:");
      System.out.println(funcEditar);

      System.out.print("Digite o novo CPF: ");
      funcEditar.setCpf(scanner.nextLine());
      System.out.print("Digite o novo nome: ");
      funcEditar.setNome(scanner.nextLine());
      System.out.print("Digite o novo horário de trabalho (HH:mm): ");
      funcEditar.setHoraTrabalho(java.time.LocalTime.parse(scanner.nextLine()));
      System.out.print("Digite o novo email: ");
      funcEditar.setEmail(scanner.nextLine());

      if (funcEditar.editar()) {
        System.out.println("Funcionário editado com sucesso!");
      } else {
        System.out.println("Erro ao editar o funcionário.");
      }
    } else {
      System.out.println("Funcionário não encontrado.");
    }
  }
}
