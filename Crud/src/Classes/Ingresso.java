package Classes;
import java.util.ArrayList;

import API.Generic;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;
    private Generic generic = new Generic<Ingresso>();
    public String filepath = "ingresso";

    // CONSTRUTOR
    public Ingresso() {
    }

    public Ingresso(int idIngresso, double valorPago, SalaAssento salaAssento, Sessao sessao) {
      this.idIngresso = idIngresso;
      this.valorPago = valorPago;
      this.salaAssento = salaAssento;
      this.sessao = sessao;
    }

// ENCAPSULAMENTO
    public int getIdIngresso() {
      return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
      this.idIngresso = idIngresso;
    }

    public double getValorPago() {
      return valorPago;
    }

    public void setValorPago(double valorPago) {
      this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
      return salaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
      this.salaAssento = salaAssento;
    }

    public Sessao getSessao() {
      return sessao;
    }

    public void setSessao(Sessao sessao) {
      this.sessao = sessao;
    }

// CADASTRAR
    public boolean cadastrar(Ingresso ingresso) {
        String dados = ingresso.getIdIngresso() + ";" + ingresso.getValorPago() + ";" + ingresso.getSalaAssento().getIdSalaAssento() + ";" + ingresso.getSessao().getIdSessao();
        return generic.register(dados, filepath);
    }

// EDITAR
    public boolean editar(Ingresso ingresso) {
        String dados = ingresso.getIdIngresso() + ";" + ingresso.getValorPago() + ";" + ingresso.getSalaAssento().getIdSalaAssento() + ";" + ingresso.getSessao().getIdSessao();
        return generic.edit(dados, filepath);
    }

// CONSULTAR
    public Ingresso consultar(String id) {
      String linha = generic.read(filepath, id);
      try {
        if (linha != null) {
          String[] partes = linha.split(";");
          if (partes.length == 4) {
          SalaAssento salaAssento = new SalaAssento(Integer.parseInt(partes[2]), null, null);
          salaAssento = salaAssento.consultar(String.valueOf(salaAssento.getIdSalaAssento()));
          Sessao sessao = new Sessao(Integer.parseInt(partes[3]), null, null, null, null, null);
          sessao = sessao.consultar(String.valueOf(sessao.getIdSessao()));
          return new Ingresso(Integer.parseInt(partes[0]), Double.parseDouble(partes[1]), salaAssento, sessao);
          }
        }
      } catch (NumberFormatException e) {
          System.err.println("Erro ao converter número: " + e.getMessage());
        }
      return null;
    }

// LISTAR
    public ArrayList<Ingresso> listar() {
    ArrayList<String> registros = generic.list(filepath);
    ArrayList<Ingresso> ingressos = new ArrayList<>();

      if (registros != null && !registros.isEmpty()) {
        for (String registro : registros) {
        String[] partes = registro.split(";");
          if (partes.length == 4) {
            SalaAssento salaAssento = new SalaAssento(Integer.parseInt(partes[2]), null, null);
            salaAssento = salaAssento.consultar(String.valueOf(salaAssento.getIdSalaAssento()));
            Sessao sessao = new Sessao(Integer.parseInt(partes[3]), null, null, null, null, null);
            sessao = sessao.consultar(String.valueOf(sessao.getIdSessao()));
            Ingresso ingresso = new Ingresso(Integer.parseInt(partes[0]), Double.parseDouble(partes[1]), salaAssento, sessao);
            ingressos.add(ingresso);
        } else {
          System.err.println("Registro mal formatado: " + registro);
        }
      }
    } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
        }
  return ingressos;
    }
}
