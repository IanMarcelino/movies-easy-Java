package Classes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import API.Generic;

public class Sessao {
    private int idSessao;
    private LocalDateTime dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String status;
    private Generic generic = new Generic<Sessao>();
    public String filepath = "sessao";

// CONSTRUTOR
    public Sessao() {
    }

    public Sessao(int idSessao, LocalDateTime dataHoraSessao, Filme filme, Sala sala, Funcionario funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }

// ENCAPSULAMENTO
    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

// CADASTRAR
    public boolean cadastrar(Sessao sessao) {
        String dados = sessao.getIdSessao() + ";" + sessao.getDataHoraSessao() + ";" + sessao.getFilme().getIdFilme() + ";" + sessao.getSala().getIdSala() + ";" + sessao.getFuncionario().getMatricula() + ";" + sessao.getStatus();
        return generic.register(dados, filepath);
    }

// EDITAR
    public boolean editar(Sessao sessao) {
        String dados = sessao.getIdSessao() + ";" + sessao.getDataHoraSessao() + ";" + sessao.getFilme().getIdFilme() + ";" + sessao.getSala().getIdSala() + ";" + sessao.getFuncionario().getMatricula() + ";" + sessao.getStatus();
        return generic.edit(dados, filepath);
    }

// CONSULTAR
    public Sessao consultar(String id) {
      String linha = generic.read(filepath, id);
      if (linha != null) {
          String[] partes = linha.split(";");
          if (partes.length == 6) {
            Filme filme = new Filme(Integer.parseInt(partes[2]));
            filme = filme.consultar(String.valueOf(filme.getIdFilme()));
            Sala sala = new Sala(Integer.parseInt(partes[3]));
            sala = sala.consultar(String.valueOf(sala.getIdSala()));
            Funcionario funcionario = new Funcionario(Integer.parseInt(partes[4]));
            funcionario = funcionario.consultar(String.valueOf(funcionario.getMatricula()));
            return new Sessao(Integer.parseInt(partes[0]), LocalDateTime.parse(partes[1]), filme, sala, funcionario, partes[5]);
        }
      }
     return null;
    }

// LISTAR
    public ArrayList<Sessao> listar() {
      ArrayList<String> registros = generic.list(filepath);
      ArrayList<Sessao> sessoes = new ArrayList<>();

      if (registros != null && !registros.isEmpty()) {
          for (String registro : registros) {
            String[] partes = registro.split(";");
            if (partes.length == 6) {
            Filme filme = new Filme(Integer.parseInt(partes[2]));
            filme = filme.consultar(String.valueOf(filme.getIdFilme()));
            Sala sala = new Sala(Integer.parseInt(partes[3]));
            sala = sala.consultar(String.valueOf(sala.getIdSala()));
            Funcionario funcionario = new Funcionario(Integer.parseInt(partes[4]));
            funcionario = funcionario.consultar(String.valueOf(funcionario.getMatricula()));
            Sessao sessao = new Sessao(Integer.parseInt(partes[0]), LocalDateTime.parse(partes[1]), filme, sala, funcionario, partes[5]);
            sessoes.add(sessao);
              } else {
              System.err.println("Registro mal formatado: " + registro);
            }
          }
      } else {
        System.out.println("Nenhum registro encontrado ou arquivo inexistente.");
        }
      return sessoes;
    }

// TOSTRING
    @Override
    public String toString() {
        return "Sessao{" +
          "idSessao=" + idSessao +
          ", dataHoraSessao=" + dataHoraSessao +
          ", filme=" + (filme != null ? filme.toString() : "null") +
          ", sala=" + (sala != null ? sala.toString() : "null") +
          ", funcionario=" + (funcionario != null ? funcionario.toString() : "null") +
          ", status='" + status + '\'' +
          '}';
    }
}