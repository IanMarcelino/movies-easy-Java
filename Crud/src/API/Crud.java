package API;

import java.util.ArrayList;

public interface Crud<O> {
     public boolean register(String dados, String tipoArquivo); 
     public boolean edit(String dados,String tipoArquivo);
     public String read(String tipoArquivo, String cpf);
     public ArrayList <String> list(String tipoArquivo);
}
