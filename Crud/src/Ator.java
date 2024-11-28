import java.util.ArrayList;

import API.Generic;

public class Ator extends Pessoa {
  private int registro;
  private String filepath = "ator";
  private Generic generic = new Generic<Ator>();

   public Ator(String cpf, String nome, String email, int registro){
    super(cpf, nome, email);
    this.registro = registro;
   }
   public void setRegistro(int registro) {
     this.registro = registro;
   }
   public int getRegistro() {
     return registro;
   }
   

public boolean cadastrar() {
  String dados = this.getRegistro() + ";" + this.getCpf() + ";" + this.getNome() + ";" + this.getEmail();
  return generic.register(dados,filepath);
}

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

}