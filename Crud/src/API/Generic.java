// Imports
package API;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Atributos e Encapsulamento 
  public class Generic<G> {
  private String filepath;

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public String getFilepath() {
    return filepath;
  }

// Métodos Gerais 
  public boolean register(String dados,String tipoArquivo) {
    String filepath = "F:\\Crud\\src\\database\\" + tipoArquivo + ".txt";
      try {
        //Verificar se os dados já existem no arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
          String line;
          while ((line = br.readLine()) != null) {
          if (line.equals(dados)) {
          System.out.println("Registro já existe: " + dados);
          return false; // Dados duplicados, não grava
        }
    }
}
          // Se não encontrou duplicatas, grava os dados no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true))) {
          bw.write(dados);
          bw.newLine();
          System.out.println("Registro adicionado: " + dados);
          return true; // Dados gravados com sucesso
          }
        } catch (IOException e) {
            System.err.println("Erro ao registrar: " + e.getMessage());
            return false;
        }
  }
}