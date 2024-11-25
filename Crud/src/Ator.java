import API.Generic;

public class Ator extends Pessoa {
  private int registro;
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
   

public boolean cadastrar(Ator ator, Generic<String> generic) {
String data = ator.getRegistro() + ";" + ator.getCpf() + ";" + ator.getNome() + ";" + ator.getEmail();
// Usa a classe Generic para registrar os dados
return generic.register(data,ator);
}


}
