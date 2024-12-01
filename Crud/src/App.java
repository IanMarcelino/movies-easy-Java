import java.time.LocalTime;
import java.util.ArrayList;

import API.Generic;

public class App {
    public static void main(String[] args) {
                        //CADASTRAR
        Funcionario ian = new Funcionario("1234","Ian","ian@",12,LocalTime.of(12, 0));
        if (ian.cadastrar()){
        System.out.println("Cadastrado com sucesso");
        } else {
        System.out.println("Cadastro falho");
        }



                        //CONSULTAR
        // Ator ator = new Ator("", "", "", 0);
        // Ator resultado = ator.consultar("06047832300");

        // try {
        //     if (resultado != null) {
        //         System.out.println("Processando dados...");
        //         Thread.sleep(1500); // Pausa de 1.5 segundos
        //         System.out.println("Ator encontrado: ");
        //         Thread.sleep(1500); // Pausa de 1.5 segundos
        //         System.out.println("Registro: " + resultado.getRegistro());
        //         Thread.sleep(1500); // Pausa de 1.5 segundos
        //         System.out.println("Nome: " + resultado.getNome());
        //         Thread.sleep(1500); // Pausa de 1.5 segundos
        //         System.out.println("Email: " + resultado.getEmail());
        //     } else {
        //         System.out.println("Nenhum ator encontrado com este CPF.");
        //     }
        // } catch (InterruptedException e) {
        //     System.err.println("Ocorreu um erro ao pausar a execução: " + e.getMessage());
        // }



        //             //EDITAR
        // Ator ator = new Ator("12345", "IAN", "IANCAVALCANTE9", 1);
        // boolean resultado = ator.editar();
        // if (resultado) {
        //     System.out.println("\nDados do ator atualizados com sucesso!");
        // } else {
        //     System.out.println("\nErro ao tentar atualizar os dados do ator.");
        // }
        // if (resultado) {

        //     System.out.println("\nApós a edição:");
        //     System.out.println("CPF: " + ator.getCpf());
        //     System.out.println("Nome: " + ator.getNome());
        //     System.out.println("Email: " + ator.getEmail());
        //     System.out.println("Registro: " + ator.getRegistro());
        // }



                    //LISTAR
        // Ator atores = new Ator(null, null, null, 0);
        // for(Ator ator : atores.listar()){
        // System.out.println("Registro " + ator.getRegistro());
        // System.out.println("Nome " + ator.getNome());
        // System.out.println("CPF " + ator.getCpf());
        // System.out.println("Email " + ator.getEmail());
        // System.out.println("----------------------------------------");
        // }
    }
}
