package Classes;
import java.util.ArrayList;

import API.Generic;

public class Filme {
  private int idFilme;
  private String titulo;
  private int classificacao;
  private Genero genero; 
  private String status;
  private Generic generic = new Generic<Filme>(); // Deve ser do tipo Filme, não Ator
  public String filepath = "filme";


//CONSTRUTOR
  public Filme(int id) {
    this.generic = new Generic<Genero>();
    this.idFilme = id;
}
  public Filme(int idFilme, String titulo, int classificacao, Genero genero , String status){ 
    this.idFilme = idFilme;
    this.titulo = titulo;
    this.classificacao = classificacao;
    this.genero = genero; 
    this.status = status;
  }


  //ENCAPSULAMENTO
  public void setClassificacao(int classificacao) {
    this.classificacao = classificacao;
  }
  public void setGenero(Genero genero) {
    this.genero = genero;
  }
  public void setIdFilme(int idFilme) {
    this.idFilme = idFilme;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public int getClassificacao() {
    return classificacao;
  }
  public Genero getGenero() {
    return genero;
  }
  public int getIdFilme() {
    return idFilme;
  }
  public String getStatus() {
    return status;
  }
  public String getTitulo() {
    return titulo;
  }


  @Override
public String toString() {
    return "Filme{" +
            "idFilme=" + idFilme +
            ", titulo='" + titulo + '\'' +
            ", classificacao=" + classificacao +
            ", genero=" + (genero != null ? genero.toString() : "null") +
            ", status='" + status + '\'' +
            '}';
}
  

  // CADASTRAR
  public boolean cadastrar() {
    String dados = this.getIdFilme() + ";" + this.getTitulo() + ";" + this.getClassificacao() + ";" + this.getGenero().getId() + ";" + this.getStatus();
    return generic.register(dados, filepath); // Cadastro correto
  }


  // CONSULTAR
  public Filme consultar(String id) {
    // Buscar o filme pelo ID
    String linha = generic.read(filepath, id); 
    if (linha != null) {
        String[] partes = linha.split(";");
        if (partes.length == 5) { 
          String idGenero = partes[3]; // ID do Gênero
          String linhaGenero = generic.read("genero", idGenero); // Aqui você consulta no arquivo de gêneros
          Genero genero = null;
          if (linhaGenero != null) {
              String[] partesGenero = linhaGenero.split(";");
              if (partesGenero.length == 3) {
                  genero = new Genero(Integer.parseInt(partesGenero[0]), partesGenero[1], partesGenero[2]);
              }
          }
          return new Filme(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), genero, partes[4]);
      }
  }
  return null; 
}


//LISTAR
public ArrayList<Filme> listar() {
    ArrayList<String> registros = generic.list(filepath);
    ArrayList<Filme> filmes = new ArrayList<>();

    if (registros != null && !registros.isEmpty()) {
        for (String registro : registros) {
            String[] partes = registro.split(";");
            if (partes.length == 5) { 
                Genero genero = new Genero(Integer.parseInt(partes[3]));
                Filme filme = new Filme(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), genero, partes[4]);
                filmes.add(filme);
            } else {
                System.err.println("Registro mal formatado: " + registro);
            }
        }
    } else {
        System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
    }
    return filmes;
}


//EDITAR
public boolean editar() {
  String dados = this.getIdFilme() + ";" + this.getTitulo() + ";" + this.getClassificacao() + ";" + this.getGenero().getId() + ";" + this.getStatus();
  return generic.edit(dados, filepath);
}

}
