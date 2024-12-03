package Classes;
import java.util.ArrayList;

import API.Generic;

public class Genero {
  private int id;
  private String descricao;
  private String status;
  private Generic generic = new Generic<Genero>();
  public String filepath = "genero";

  
  //Construtor
  public Genero(int id, String descricao, String status){
    this.id = id;
    this.descricao = descricao; 
    this.status = status;
  }
  public Genero(int id) {
    this.id = id;
}
  //Encapsulamento
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getDescricao() {
    return descricao;
  }
  public int getId() {
    return id;
  }
  public String getStatus() {
    return status;
  }

  @Override
public String toString() {
    return "Genero{" +
            "id=" + id +
            ", descricao='" + descricao + '\'' +
            ", status='" + status + '\'' +
            '}';
}


  //Métodos do CRUD
  //CADASTRAR
  public boolean cadastrar() {
    String dados = this.getId() + ";" + this.getDescricao() + ";" + this.getStatus();
    return generic.register(dados,filepath);
  }

  public ArrayList<Genero> listar() {
  ArrayList<String> registros = generic.list(filepath);
  ArrayList<Genero> generos = new ArrayList<>();

  if (registros != null && !registros.isEmpty()) {
      for (String registro : registros) {
          String[] partes = registro.split(";");
          if (partes.length == 3) {
              Genero genero = new Genero(Integer.parseInt(partes[0]),partes[1], partes[2]);
              generos.add(genero);
          } else {
              System.err.println("Registro mal formatado: " + registro);
          }
      }
  } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
  }
  return generos;
}
public Genero consultar(String id) {
  String linha = generic.read(filepath, id); 
  if (linha != null) {
      String[] partes = linha.split(";");
      if (partes.length == 3) { 
          return new Genero(Integer.parseInt(partes[0]),partes[1], partes[2]);
      }
  }
  return null; 
}
public boolean editar() {
  String dados = this.getId() + ";" + this.getDescricao() + ";" + this.getStatus();
  return generic.edit(dados, filepath);
}
}
