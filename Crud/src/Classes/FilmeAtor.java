package Classes;
import java.util.ArrayList;

import API.Generic;

public class FilmeAtor {
  private int idFilme;
  private Ator ator;
  private Filme filme;
  private String personagem;
  private boolean principal;
  private Generic generic = new Generic<FilmeAtor>();
  public String filepath = "FilmeAtor";

  public FilmeAtor(int idFilme, Ator ator, Filme filme, String personagem, boolean principal){
    this.idFilme = idFilme;
    this.ator = ator;
    this.filme = filme;
    this.personagem = personagem;
    this.principal = principal;
  }

  public void setAtor(Ator ator) {
    this.ator = ator;
  }
  public void setFilme(Filme filme) {
    this.filme = filme;
  }
  public void setIdFilme(int idFilme) {
    this.idFilme = idFilme;
  }
  public void setPersonagem(String personagem) {
    this.personagem = personagem;
  }
  public void setPrincipal(boolean principal) {
    this.principal = principal;
  }
  public Ator getAtor() {
    return ator;
  }
  public Filme getFilme() {
    return filme;
  }
  public int getIdFilme() {
    return idFilme;
  }
  public String getPersonagem() {
    return personagem;
  }
  public boolean getPrincipal(){
    return principal;
  }


  @Override
public String toString() {
    return "FilmeAtor{" +
            "idFilme=" + idFilme +
            ", ator=" + (ator != null ? ator.toString() : "null") +
            ", filme=" + (filme != null ? filme.toString() : "null") +
            ", personagem='" + personagem + '\'' +
            ", principal=" + principal +
            '}';
}


  // CADASTRAR
  public boolean cadastrar() {
    String dados = this.getIdFilme() + ";" + this.getAtor().getRegistro() + ";" + this.getFilme().getIdFilme() + ";" + this.getPersonagem() + ";" + this.getPrincipal();
    return generic.register(dados, filepath); 
  }

  // CONSULTAR
  public FilmeAtor consultar(String id) {
    String linha = generic.read(filepath, id); 
    if (linha != null) {
        String[] partes = linha.split(";");
        if (partes.length == 5) { 
          int idFilme = Integer.parseInt(partes[0]);
          String idAtor = partes[1]; 
          int filmeId = Integer.parseInt(partes[2]);
          String personagem = partes[3];
          boolean principal = Boolean.parseBoolean(partes[4]);

          Filme filme = new Filme(filmeId);  
          filme = filme.consultar(String.valueOf(filmeId));  //CONSULTAR FILME

          Ator ator = new Ator(Integer.parseInt(idAtor));  
          ator = ator.consultar(idAtor);  

          if (filme != null && ator != null) {
              return new FilmeAtor(idFilme, ator, filme, personagem, principal);
          }
      }
  }
  return null; 
}
  //LISTAR
public ArrayList<FilmeAtor> listar() {
  ArrayList<String> registros = generic.list(filepath); 
  ArrayList<FilmeAtor> filmesAtor = new ArrayList<>(); 
  if (registros != null && !registros.isEmpty()) {
    for (String registro : registros) {
      try {
        String[] partes = registro.split(";");  
          if (partes.length == 5) {  
            int idFilme = Integer.parseInt(partes[0]); 
            int idAtor = Integer.parseInt(partes[1]); 
            int filmeId = Integer.parseInt(partes[2]); 
            String personagem = partes[3];  
            boolean principal = Boolean.parseBoolean(partes[4]);  

            Filme filme = new Filme(filmeId);  
            filme = filme.consultar(String.valueOf(filmeId));  

            Ator ator = new Ator(idAtor);  
            ator = ator.consultar(String.valueOf(idAtor));  

                  
            if (filme != null && ator != null) {
            FilmeAtor filmeAtor = new FilmeAtor(idFilme, ator, filme, personagem, principal);
            filmesAtor.add(filmeAtor);  
            } else {
            System.err.println("Erro ao consultar Filme ou Ator para o registro: " + registro);
            }
            } else {
            System.err.println("Registro mal formatado: " + registro);
            }
          } catch (NumberFormatException e) {
              System.err.println("Erro de formato ao tentar converter o número no registro: " + registro);
          }
      }
  } else {
      System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
  }
  return filmesAtor;
}

//EDITAR 
public boolean editar() {
  String dados = this.getIdFilme() + ";" + this.getAtor().getRegistro() + ";" + this.getFilme().getIdFilme() + ";" + this.getPersonagem() + ";" + this.getPrincipal();
  return generic.edit(dados, filepath);
}
 
}
