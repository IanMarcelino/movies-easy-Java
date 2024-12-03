package Classes;
import java.util.ArrayList;
import API.Generic;

public class TipoAssento {
  private int idTipoAssento;
  private String descricao;
  private String status;
  private Generic generic = new Generic<TipoAssento>();
  public String filepath = "tipoAssento";

  // CONSTRUTOR
  public TipoAssento() {
  }

  public TipoAssento(int idTipoAssento, String descricao, String status) {
      this.idTipoAssento = idTipoAssento;
      this.descricao = descricao;
      this.status = status;
  }

  // ENCAPSULAMENTO
  public int getIdTipoAssento() {
      return idTipoAssento;
  }

  public String getDescricao() {
      return descricao;
  }

  public String getStatus() {
      return status;
  }

  public void setIdTipoAssento(int idTipoAssento) {
      this.idTipoAssento = idTipoAssento;
  }

  public void setDescricao(String descricao) {
      this.descricao = descricao;
  }

  public void setStatus(String status) {
      this.status = status;
  }

  // TOSTRING
  @Override
  public String toString() {
      return "TipoAssento{" +
              "idTipoAssento=" + idTipoAssento +
              ", descricao='" + descricao + '\'' +
              ", status='" + status + '\'' +
              '}';
  }

  // CADASTRAR
  public boolean cadastrar() {
      String dados = this.getIdTipoAssento() + ";" + this.getDescricao() + ";" + this.getStatus();
      return generic.register(dados, filepath);
  }

  // LISTAR
  public ArrayList<TipoAssento> listar() {
      ArrayList<String> registros = generic.list(filepath);
      ArrayList<TipoAssento> tipoAssentos = new ArrayList<>();

      if (registros != null && !registros.isEmpty()) {
          for (String registro : registros) {
              String[] partes = registro.split(";");
              if (partes.length == 3) {
                  TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(partes[0]), partes[1], partes[2]);
                  tipoAssentos.add(tipoAssento);
              } else {
                  System.err.println("Registro mal formatado: " + registro);
              }
          }
      } else {
          System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
      }
      return tipoAssentos;
  }

  // CONSULTAR
  public TipoAssento consultar(String id) {
    String linha = generic.read(filepath, id);
    if (linha != null) {
         String[] partes = linha.split(";");
        if (partes.length == 3) {
          return new TipoAssento(Integer.parseInt(partes[0]), partes[1], partes[2]);
        }
      }
      return null;
  }

  // EDITAR
  public boolean editar() {
    String dados = this.getIdTipoAssento() + ";" + this.getDescricao() + ";" + this.getStatus();
    return generic.edit(dados, filepath);
  }
}

