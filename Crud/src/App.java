import java.util.ArrayList;

import API.Generic;

public class App {
    public static void main(String[] args) {


       Ator atores = new Ator(null, null, null, 0);

       for(Ator ator : atores.listar()){
           System.out.println("Registro " + ator.getRegistro());
           System.out.println("Nome " + ator.getNome());
           System.out.println("CPF " + ator.getCpf());
           System.out.println("Email " + ator.getEmail());
           System.out.println("----------------------------------------");
       }
        }
    }
    