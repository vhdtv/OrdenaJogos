import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public static void lerArquivo(){
        ArrayList<Item> listaDeJogos = new ArrayList<>();
        String arquivoCSV = "JogosDesordenados.csv";
        try{
            Scanner sc = new Scanner(new File(arquivoCSV));

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                listaDeJogos.add(linha);
                
                
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.err.println("Arquivo não encontrado:" + arquivoCSV);
            e.printStackTrace();
        }
    }
}
