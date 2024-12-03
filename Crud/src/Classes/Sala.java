package Classes;
import java.util.ArrayList;
import API.Generic;

public class Sala {
  private int idSala;
  private int capacidadeSala;
  private String descricao;
  private String status;
  private Generic generic = new Generic<Sala>();
  public String filepath = "sala";


  //CONSTRUTORES
  public Sala(int idSala, int capacidadeSala, String descricao, String status){
    this.idSala=idSala;
    this.capacidadeSala = capacidadeSala;
    this.descricao = descricao;
    this.status = status;
  }
  public Sala(int idSala) {
    this.idSala = idSala;
}


//ENCAPSULAMENTO
  public void setCapacidadeSala(int capacidadeSala) {
    this.capacidadeSala = capacidadeSala;
  }
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setIdSala(int idSala) {
    this.idSala = idSala;
  }
  public int getCapacidadeSala() {
    return capacidadeSala;
  }
  public String getDescricao() {
    return descricao;
  }
  public int getIdSala() {
    return idSala;
  }
  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
      return "Sala{" +
              "idSala=" + idSala +
              ", capacidadeSala=" + capacidadeSala +
              ", descricao='" + descricao + '\'' +
              ", status='" + status + '\'' +
              '}';
  }
  
//CADASTRAR
public boolean cadastrar() {
  String dados = this.getIdSala() + ";" + this.getCapacidadeSala() + ";" + this.getDescricao()+ ";" + this.getStatus();
  return generic.register(dados,filepath);
}


//LISTAR
public ArrayList<Sala> listar() {
  ArrayList<String> registros = generic.list(filepath);
  ArrayList<Sala> salas = new ArrayList<>();

  if (registros != null && !registros.isEmpty()) {
      for (String registro : registros) {
          String[] partes = registro.split(";");
          if (partes.length == 4) {
              Sala sala = new Sala(Integer.parseInt(partes[0]),Integer.parseInt(partes[1]), partes[2],partes[3]);
              salas.add(sala);
          } else {
              System.err.println("Registro mal formatado: " + registro);
          }
      }
  } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
  }
  return salas;
}


//CONSULTAR
public Sala consultar(String id) {
  String linha = generic.read(filepath, id); 
  if (linha != null) {
      String[] partes = linha.split(";");
      if (partes.length == 4) { 
          return new Sala(Integer.parseInt(partes[0]),Integer.parseInt(partes[1]), partes[2],partes[3]);
      }
  }
  return null; 
}


//EDITAR
public boolean editar() {
    String dados = this.getIdSala() + ";" + this.getCapacidadeSala() + ";" + this.getDescricao()+ ";" + this.getStatus();
    return generic.edit(dados, filepath);
}
}
