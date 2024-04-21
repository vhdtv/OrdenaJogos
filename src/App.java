import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Item> listaDeJogos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("[1] Ler arquivo");
            System.out.println("[2] Ordenar por categoria");
            System.out.println("[3] Ordenar por avaliação");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    lerArquivo();
                    break;
                case 2:
                    ordenarPorCategoria();
                    break;
                case 3:
                    ordenarPorAvaliacao();
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 4);
    }

    public static void lerArquivo() {
        String arquivoCSV = "C:\\Users\\vhdtv\\Desktop\\Faculdade\\Programação\\OrdenaJogos-main\\src\\JogosDesordenados.csv";
        try {
            Scanner sc = new Scanner(new File(arquivoCSV));
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String delimitador = ",";
                String[] tokens = linha.split(delimitador);
                String jogo = tokens[0];
                String categoria = tokens[1];
                Double avaliacao = Double.parseDouble(tokens[2]);
                
                // Verifica se o jogo já existe na lista antes de adicioná-lo
                boolean jogoRepetido = false;
                for (Item item : listaDeJogos) {
                    if (item.getJogos().equals(jogo)) {
                        jogoRepetido = true;
                        break;
                    }
                }
                if (!jogoRepetido) {
                    listaDeJogos.add(new Item(jogo, categoria, avaliacao));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado:" + arquivoCSV);
            e.printStackTrace();
        }
    }
    

    public static void ordenarPorCategoria() {
        Collections.sort(listaDeJogos, Comparator.comparing(Item::getCategoria));
        salvarParaArquivo(listaDeJogos, "JogosOrdenadosporCategoria.csv");
        System.out.println("Jogos ordenados por categoria e salvos em JogosOrdenadosporCategoria.csv");
    }

    public static void ordenarPorAvaliacao() {
        Collections.sort(listaDeJogos, Comparator.comparing((Item item) -> -item.getAvaliacao()));
        salvarParaArquivo(listaDeJogos, "JogosOrdenadosporAvaliacao.csv");
        System.out.println("Jogos ordenados por avaliação e salvos em JogosOrdenadosporAvaliacao.csv");
    }

    static void salvarParaArquivo(ArrayList<Item> listaDeJogos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Item item : listaDeJogos) {
                writer.println(item.getJogos() + "," + item.getCategoria() + "," + item.getAvaliacao());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
