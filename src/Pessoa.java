public abstract class Pessoa {
private String cpf; 
private String nome;
private String email;

public Pessoa(String cpf, String nome, String email){
  this.cpf = cpf;
  this.nome = nome; 
  this.email = email;5
}

public void setCpf(String cpf){
  this.cpf = cpf;
}
public void setNome(String nome) {
  this.nome = nome;
}
public void setEmail(String email) {
  this.email = email;
}
public String getCpf() {
  return cpf;
}
public String getEmail() {
  return email;
}
public String getNome() {
  return nome;
}

}