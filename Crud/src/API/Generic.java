// Imports
package API;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

// Atributos e Encapsulamento 
  public class Generic<G> implements Crud {
  private String filepath;

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public String getFilepath() {
    return filepath;
  }


                        //Método Cadastrar
public boolean register(String dados, String tipoArquivo){
    String filepath = "C:\\Users\\User\\Desktop\\JAVA_AV3\\Crud\\src\\database\\" + tipoArquivo + ".txt";
    Path caminhoArquivo = Paths.get(filepath);  

    try {
        if (!Files.exists(caminhoArquivo)) {
            Files.createFile(caminhoArquivo);
        }

        
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
        System.err.println("Erro ao registrar seu cadastro.");
        return false;
        }

}
                        //Método Listar
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
        System.err.println("Erro no processamento da lista.");
        return null;
    }
}

                            //Método Consultar
public String read(String tipoArquivo, String dados) {
    String filepath = "C:\\Users\\User\\Desktop\\JAVA_AV3\\Crud\\src\\database\\" + tipoArquivo + ".txt";
    Path caminhoArquivo = Paths.get(filepath);

    try {
        if (Files.exists(caminhoArquivo)) {
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(";");
                    if (partes.length > 1 && partes[0].trim().equals(dados.trim())) {
                        return linha; 
                    }
                }
            }
        } else {
            System.out.println("O arquivo não existe.");
        }
    } catch (IOException e) {
        System.err.println("Erro na consulta cadastral." );
    }
    return null; 
}

                            //Método Editar
public boolean edit(String dados,String tipoArquivo){
    String filepath = "C:\\Users\\User\\Desktop\\JAVA_AV3\\Crud\\src\\database\\" + tipoArquivo + ".txt";
    Path caminhoArquivo = Paths.get(filepath);
    boolean att = false;
    try {
        List<String> linhas = Files.readAllLines(caminhoArquivo);
        List<String> novasLinhas = new ArrayList<>();

        String[] partesEditadas = dados.split(";");
        String idEditado = partesEditadas[0];

        for (String linha : linhas){
            String[] partes = linha.split(";");

            if (partes[0].equals(idEditado)) {
                novasLinhas.add(dados);
                att = true;
            } else {
                novasLinhas.add(linha);
            }
        }

        Files.write(caminhoArquivo, novasLinhas);
        if (att) {
            System.out.println("Registro atualizado com sucesso!");
        } else {
            System.out.println("ID não encontrado.");
        }

        return att;
    } catch(IOException e) {
        System.out.println("Erro na atualização de cadastro.");
        return false;
    }

}
}
