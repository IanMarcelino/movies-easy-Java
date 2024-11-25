package API;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generic<G> {
  private String filepath;

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public String getFilepath() {
    return filepath;
  }

  public boolean register(String data,String arquivo) {
    String filepath = "src/database/tables/" + arquivo + ".txt";
    try (
        
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr)) {
      String line;
      // Verifica se já existe o dado no arquivo
      while ((line = br.readLine()) != null) {
        if (line.equals(data)) { // Verifica duplicidade
          System.out.println("Registro já existe: " + data);
          return false; 
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
      return false; 
    }

    try (
        // Abrindo o arquivo para escrita
        FileWriter fw = new FileWriter(filepath, true);
        BufferedWriter bw = new BufferedWriter(fw)) {
      // Adiciona o novo dado
      bw.write(data);
      bw.newLine();
      System.out.println("Registro adicionado: " + data);
      return true; // Retorna verdadeiro indicando sucesso
    } catch (IOException e) {
      System.err.println("Erro ao registrar: " + e.getMessage());
      return false; // Retorna falso em caso de erro ao escrever
    }
  }
}
