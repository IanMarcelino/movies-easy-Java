// Imports
package API;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

// Atributos e Encapsulamento 
  public class Generic<G> {
  private String filepath;

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public String getFilepath() {
    return filepath;
  }

// __________________Métodos Cadastrar__________________
public boolean register(String dados, String tipoArquivo){
    String filepath = "C:\\Users\\User\\Desktop\\JAVA_AV3\\Crud\\src\\database\\" + tipoArquivo + ".txt";
    Path caminhoArquivo = Paths.get(filepath);  

    try {
        if (!Files.exists(caminhoArquivo)) {
            Files.createFile(caminhoArquivo);
        }

        // Verifica se os dados já existem no arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.equals(dados)) { 
                    System.out.println("Registro existente: " + dados);
                    return false; 
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo.toFile(), true))) {
            bw.write(dados);
            bw.newLine();
            System.out.println("Registro adicionado: " + dados);
            return true; 
        }            
    } catch (IOException e) {
        System.err.println("Erro ao registrar: " + e.getMessage());
        return false;
        }

}
//_________________Método Listar__________________
public ArrayList<String> list(String tipoArquivo) {
    ArrayList<String> registros = new ArrayList<>();
    String filepath = "C:\\Users\\User\\Desktop\\JAVA_AV3\\Crud\\src\\database\\" + tipoArquivo + ".txt";
    Path caminhoArquivo = Paths.get(filepath);

    try {
        if (Files.exists(caminhoArquivo)) {
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    registros.add(linha); 
                }
            }
        } else {
            System.out.println("O arquivo não existe.");
        }
        return registros;
    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        return null;
    }
}
}
