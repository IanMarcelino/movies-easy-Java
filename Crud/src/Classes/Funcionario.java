package Classes;

import java.time.LocalTime;
import java.util.ArrayList;

import API.Generic;

public class Funcionario extends Pessoa {
  private int matricula;
  private LocalTime horaTrabalho;
  private Generic generic = new Generic<Funcionario>();
  public String filepath = "funcionario";

  // CONSTRUTOR
  public Funcionario(int matricula, String cpf, String nome, LocalTime horaTrabalho, String email) {
    super(cpf, nome, email);
    this.matricula = matricula;
    this.horaTrabalho = horaTrabalho;
  }

  public Funcionario(int matricula) {

  }

  public Funcionario(String string) {

  }

  // ENCAPSULAMENTO
  public void setMatricula(int matricula) {
    this.matricula = matricula;
  }

  public int getMatricula() {
    return matricula;
  }

  public void setHoraTrabalho(LocalTime horaTrabalho) {
    this.horaTrabalho = horaTrabalho;
  }

  public LocalTime getHoraTrabalho() {
    return horaTrabalho;
  }

  @Override
  public String toString() {
    return "Funcionario{" +
        "matricula=" + matricula +
        ", cpf='" + getCpf() + '\'' +
        ", nome='" + getNome() + '\'' +
        ", horaTrabalho=" + horaTrabalho +
        ", email='" + getEmail() + '\'' +
        '}';
  }

  // CADASTRAR
  public boolean cadastrar() {
    String dados = this.getMatricula() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getHoraTrabalho() + ";"
        + this.getEmail();
    return generic.register(dados, filepath);
  }

  // LISTAR
  public ArrayList<Funcionario> listar() {
    ArrayList<String> registros = generic.list(filepath);
    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    if (registros != null && !registros.isEmpty()) {
      for (String registro : registros) {
        String[] partes = registro.split(";");
        if (partes.length == 5) {
          Funcionario funcionario = new Funcionario(Integer.parseInt(partes[0]), partes[1], partes[2],
              LocalTime.parse(partes[3]), partes[4]);
          funcionarios.add(funcionario);
        } else {
          System.err.println("Registro mal formatado: " + registro);
        }
      }
    } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
    }
    return funcionarios;
  }

  // CONSULTAR
  public Funcionario consultar(String dados) {
    String linha = generic.read(filepath, dados);
    if (linha != null) {
      String[] partes = linha.split(";");
      if (partes.length == 5) {
        return new Funcionario(Integer.parseInt(partes[0]), partes[1], partes[2], LocalTime.parse(partes[3]),
            partes[4]);
      }
    }
    return null;
  }

  // EDITAR
  public boolean editar() {
    String dados = this.getMatricula() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getEmail() + ";"
        + this.getHoraTrabalho();
    return generic.edit(dados, filepath);
  }
}
