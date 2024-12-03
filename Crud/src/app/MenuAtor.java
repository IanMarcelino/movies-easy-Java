package app;

import java.util.Scanner;
import API.Generic;
import java.util.ArrayList;
import Classes.Ator;

public class MenuAtor {
    private Scanner scanner = new Scanner(System.in);
    private Ator ator = new Ator(""); //ACESSAR OS METODOS - PRECISA INSTANCIAR

    public void iniciarMenu() {
        boolean running = true;

        while (running) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    cadastrarAtor();
                    break;
                case "2":
                    listarAtores();
                    break;
                case "3":
                    consultarAtor();
                    break;
                case "4":
                    editarAtor();
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
        System.out.println("Gerenciamento de Atores");
        System.out.println("1 - Cadastrar Ator");
        System.out.println("2 - Listar Atores");
        System.out.println("3 - Consultar Ator");
        System.out.println("4 - Editar Ator");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
    }

    private void cadastrarAtor() {
        System.out.println("Cadastro de Ator:");
        System.out.print("Digite o registro do ator: ");
        int registro = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o CPF do ator: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o nome do ator: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do ator: ");
        String email = scanner.nextLine();

        Ator novoAtor = new Ator(cpf, nome, email, registro);
        if (novoAtor.cadastrar()) {
            System.out.println("Ator cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar o ator. Verifique se o registro já existe.");
        }
    }

    private void listarAtores() {
        System.out.println("Lista de Atores:");
        ArrayList<Ator> atores = ator.listar();

        if (atores != null && !atores.isEmpty()) {
            for (Ator ator : atores) {
                System.out.println(ator);
            }
        } else {
            System.out.println("Nenhum ator encontrado.");
        }
    }

    private void consultarAtor() {
        System.out.print("Digite o registro do ator que deseja consultar: ");
        String registro = scanner.nextLine();

        Ator atorConsultado = ator.consultar(registro);
        if (atorConsultado != null) {
            System.out.println("Ator encontrado:");
            System.out.println(atorConsultado);
        } else {
            System.out.println("Ator não encontrado.");
        }
    }

    private void editarAtor() {
        System.out.print("Digite o registro do ator que deseja editar: ");
        String registro = scanner.nextLine();

        Ator atorEditar = ator.consultar(registro);
        if (atorEditar != null) {
            System.out.println("Ator atual:");
            System.out.println(atorEditar);

            System.out.print("Digite o novo CPF do ator: ");
            atorEditar.setCpf(scanner.nextLine());
            System.out.print("Digite o novo nome do ator: ");
            atorEditar.setNome(scanner.nextLine());
            System.out.print("Digite o novo email do ator: ");
            atorEditar.setEmail(scanner.nextLine());

            if (atorEditar.editar()) {
                System.out.println("Ator editado com sucesso!");
            } else {
                System.out.println("Erro ao editar o ator.");
            }
        } else {
            System.out.println("Ator não encontrado.");
        }
    }
}