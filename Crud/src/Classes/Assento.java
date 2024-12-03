package Classes;
import java.util.ArrayList;

import API.Generic;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;
    private Generic generic = new Generic<Assento>();
    public String filepath = "assento";

// CONSTRUTOR
    public Assento() {
    }

    public Assento(int idAssento, TipoAssento tipoAssento) {
      this.idAssento = idAssento;
      this.tipoAssento = tipoAssento;
    }

// ENCAPSULAMENTO
    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
      this.idAssento = idAssento;
    }

    public TipoAssento getTipoAssento() {
      return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
      this.tipoAssento = tipoAssento;
    }


 // TOSTRING
 @Override
 public String toString() {
     return "Assento{" +
             "idAssento=" + idAssento +
             ", tipoAssento=" + (tipoAssento != null ? tipoAssento.toString() : "null") +
             '}';
 }

 
// CADASTRAR
    public boolean cadastrar(Assento assento) {
      String dados = assento.getIdAssento() + ";" + assento.getTipoAssento().getIdTipoAssento();
      return generic.register(dados, filepath);
    }

// EDITAR
    public boolean editar(Assento assento) {
      String dados = assento.getIdAssento() + ";" + assento.getTipoAssento().getIdTipoAssento();
      return generic.edit(dados, filepath);
    }

// CONSULTAR
    public Assento consultar(String id) {
        String linha = generic.read(filepath, id);
        if (linha != null) {
        String[] partes = linha.split(";");
          if (partes.length == 2) {
            TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(partes[1]), null, null);
            tipoAssento = tipoAssento.consultar(String.valueOf(tipoAssento.getIdTipoAssento()));
            return new Assento(Integer.parseInt(partes[0]), tipoAssento);
            }
        }
        return null;
    }

// LISTAR
    public ArrayList<Assento> listar() {
      ArrayList<String> registros = generic.list(filepath);
      ArrayList<Assento> assentos = new ArrayList<>();

        if (registros != null && !registros.isEmpty()) {
          for (String registro : registros) {
            String[] partes = registro.split(";");
            if (partes.length == 2) {
              TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(partes[1]), null, null);
              tipoAssento = tipoAssento.consultar(String.valueOf(tipoAssento.getIdTipoAssento()));
              Assento assento = new Assento(Integer.parseInt(partes[0]), tipoAssento);
              assentos.add(assento);
                } else {
                  System.err.println("Registro mal formatado: " + registro);
                }
            }
        } else {
        System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
        }
        return assentos;
    }

}
