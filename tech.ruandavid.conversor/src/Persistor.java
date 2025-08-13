import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Persistor {

    public void escreverArquivo(String conteudo, String caminho){
        try (FileWriter fw = new FileWriter(caminho)){
            fw.write(conteudo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> lerArquivo(String conteudo) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(conteudo))){
            return br.lines().collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client API = new Client("https://v6.exchangerate-api.com/v6/" + System.getenv("API_KEY")  + "/codes");

        String moedasSuportadas = API.sendRequest();

        Persistor pr = new Persistor();

        pr.escreverArquivo(moedasSuportadas, "./moedas.json");
    }
}
