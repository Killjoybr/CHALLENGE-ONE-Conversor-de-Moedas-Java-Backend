import com.google.gson.Gson;
import java.util.List;

public class Moedas {
    private List<String> possibilidades;


    public static void main(String[] args) {
        Gson gson = new Gson();
        Client API = new Client("https://v6.exchangerate-api.com/v6/" + System.getenv("API_KEY")  + "/codes");

        String moedasSuportadas = API.sendRequest();

        Persistor pr = new Persistor();

        pr.escreverArquivo(moedasSuportadas, "./moedas.json");
    }
}
