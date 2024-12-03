package Classes;

import java.util.ArrayList;

import API.Generic;

public class Ator extends Pessoa {
  private int registro;
  private Generic<Ator> generic = new Generic<>();
  public String filepath = "ator";

  // CONSTRUTOR
  public Ator(String cpf, String nome, String email, int registro) {
    super(cpf, nome, email);
    this.registro = registro;
  }

  public Ator(int registro) {
    this.registro = registro;
  }

  public Ator(String string) {
  }

  // ENCAPSULAMENTO
  public void setRegistro(int registro) {
    this.registro = registro;
  }

  public int getRegistro() {
    return registro;
  }

  @Override
  public String toString() {
    return "Ator{" +
        "registro=" + registro +
        ", cpf='" + getCpf() + '\'' +
        ", nome='" + getNome() + '\'' +
        ", email='" + getEmail() + '\'' +
        '}';
  }

  // CADASTRAR
  public boolean cadastrar() {
    String dados = this.getRegistro() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getEmail();
    return generic.register(dados, filepath);
  }

  // LISTAR
  public ArrayList<Ator> listar() {
    ArrayList<String> registros = generic.list(filepath);
    ArrayList<Ator> atores = new ArrayList<>();

    if (registros != null && !registros.isEmpty()) {
      for (String registro : registros) {
        String[] partes = registro.split(";");
        if (partes.length == 4) {
          Ator ator = new Ator(partes[1], partes[2], partes[3], Integer.parseInt(partes[0]));
          atores.add(ator);
        } else {
          System.err.println("Registro mal formatado: " + registro);
        }
      }
    } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
    }
    return atores;
  }

  // CONSULTAR
  public Ator consultar(String dados) {
    String linha = generic.read(filepath, dados);
    if (linha != null) {
      String[] partes = linha.split(";");
      if (partes.length == 4) {
        return new Ator(partes[1], partes[2], partes[3], Integer.parseInt(partes[0]));
      }
    }
    return null;
  }

  // EDITAR
  public boolean editar() {
    String dados = this.getRegistro() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getEmail();
    return generic.edit(dados, filepath);
  }
}