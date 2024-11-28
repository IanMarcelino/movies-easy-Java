package API;

import java.util.ArrayList;

public interface Crud<O> {
     public boolean register(O objeto, O nome); 
     public boolean edit(O objeto);
     public String read(O objeto);
     public ArrayList <String> list(O objeto);
    //  throws IOException {
    //     String url = "src/database/tables/" + name + ".txt";
    
    //     FileWriter fw = new FileWriter(url, true);
    //     BufferedWriter bw = new BufferedWriter(fw);
    //     String[] id = valor.split(";");
    //     String[] response = this.get(id[0], name);
    
    //     if (response.length == 0) {
    //         bw.write(valor);
    //         bw.newLine();
    //         bw.close();
    //         return true;
    //     } else if (!response[0].equals(id[0])) { // Item diferente encontrado
    //         bw.write(valor);
    //         bw.newLine();
    //         bw.close();
    //         return true;
    //     } else {
    //         bw.close();
    //         System.out.println("\nIdentificador já existe!");
    //         return false;
    //     }
    // }
    
}
