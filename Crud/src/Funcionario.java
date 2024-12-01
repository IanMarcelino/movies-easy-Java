import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

import API.Generic;

public class Funcionario extends Pessoa{
  private int matricula; 
  private LocalTime horaTrabalho; 
  private Generic generic = new Generic<Funcionario>();
  public String filepath = "funcionario";

  public Funcionario(String cpf, String nome, String email, int matricula, LocalTime horaTrabalho){
    super(cpf, nome, email);
    this.matricula = matricula; 
    this.horaTrabalho = horaTrabalho;
}
public void setMatricula(int matricula){
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
public boolean cadastrar() {
  String dados = this.getMatricula() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getHoraTrabalho() + ";" + this.getEmail();
  return generic.register(dados,filepath);
}

public ArrayList<Funcionario> listar() {
  ArrayList<String> registros = generic.list(filepath);
  ArrayList<Funcionario> funcionarios = new ArrayList<>();

  if (registros != null && !registros.isEmpty()) {
      for (String registro : registros) {
          String[] partes = registro.split(";");
          if (partes.length == 5) {
              Funcionario funcionario = new Funcionario(partes[1], partes[2], partes[3], Integer.parseInt(partes[0]), LocalTime.parse(partes[4]));
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
}
