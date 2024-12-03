package Classes;
import java.util.ArrayList;
import API.Generic;

public class SalaAssento {
    private int idSalaAssento;
    private Assento assento;
    private Sala sala;
    private Generic generic = new Generic<SalaAssento>();
    public String filepath = "salaAssento";

    // CONSTRUTOR
    public SalaAssento() {
    }

    public SalaAssento(int idSalaAssento, Assento assento, Sala sala) {
      this.idSalaAssento = idSalaAssento;
      this.assento = assento;
      this.sala = sala;
    }

    // ENCAPSULAMENTO
    public int getIdSalaAssento() {
      return idSalaAssento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
      this.idSalaAssento = idSalaAssento;
    }

    public Assento getAssento() {
      return assento;
    }

    public void setAssento(Assento assento) {
      this.assento = assento;
    }

    public Sala getSala() {
      return sala;
    }

    public void setSala(Sala sala) {
      this.sala = sala;
    }
// TOSTRING
    @Override
    public String toString() {
        return "SalaAssento{" +
                "idSalaAssento=" + idSalaAssento +
                ", assento=" + (assento != null ? assento.toString() : "null") +
                ", sala=" + (sala != null ? sala.toString() : "null") +
                '}';
    }


// CADASTRAR
    public boolean cadastrar(SalaAssento salaAssento) {
      String dados = salaAssento.getIdSalaAssento() + ";" + salaAssento.getAssento().getIdAssento() + ";" + salaAssento.getSala().getIdSala();
      return generic.register(dados, filepath);
    }


// EDITAR
    public boolean editar(SalaAssento salaAssento) {
        String dados = salaAssento.getIdSalaAssento() + ";" + salaAssento.getAssento().getIdAssento() + ";" + salaAssento.getSala().getIdSala();
        return generic.edit(dados, filepath);
    }


// CONSULTAR
    public SalaAssento consultar(String id) {
      String linha = generic.read(filepath, id);
      try {
        if (linha != null) {
          String[] partes = linha.split(";");
          if (partes.length == 3) {
            Assento assento = new Assento(Integer.parseInt(partes[1]), null);
            assento = assento.consultar(String.valueOf(assento.getIdAssento()));
            Sala sala = new Sala(Integer.parseInt(partes[2]));
            sala = sala.consultar(String.valueOf(sala.getIdSala()));
            return new SalaAssento(Integer.parseInt(partes[0]), assento, sala);
         }
      }
    } catch (NumberFormatException e) {
            System.err.println("Erro na consulta da Sala-Assento, registro não encontrado ou arquivo inexistente.");
    }
   return null;
    }

    // LISTAR
    public ArrayList<SalaAssento> listar() {
      ArrayList<String> registros = generic.list(filepath);
      ArrayList<SalaAssento> salaAssentos = new ArrayList<>();

      if (registros != null && !registros.isEmpty()) {
        for (String registro : registros) {
          String[] partes = registro.split(";");
          if (partes.length == 3) {
          Assento assento = new Assento(Integer.parseInt(partes[1]), null);
          assento = assento.consultar(String.valueOf(assento.getIdAssento()));
          Sala sala = new Sala(Integer.parseInt(partes[2]));
          sala = sala.consultar(String.valueOf(sala.getIdSala()));
          SalaAssento salaAssento = new SalaAssento(Integer.parseInt(partes[0]), assento, sala);
          salaAssentos.add(salaAssento);
          } else {
            System.err.println("Registro mal formatado: " + registro);
          }
        }
      } else {
        System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
      }
    return salaAssentos;
  }


}
