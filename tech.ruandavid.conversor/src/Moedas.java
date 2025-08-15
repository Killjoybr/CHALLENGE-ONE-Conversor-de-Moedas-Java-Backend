import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Moedas {
    @SerializedName("supported_codes")
    private List<List<String>> moedasSuportadas;
    private List<String> opcoes = new ArrayList<>();

    public List<List<String>> getMoedasSuportadas() {
        return moedasSuportadas;
    }

    public void popularOpcoes(){
        Gson gson = new Gson();

        try {
            JsonReader jr = new JsonReader(new FileReader("moedas.json"));
            Moedas moedasSuportadas = gson.fromJson(jr, Moedas.class);

            for (int i = 0; i < moedasSuportadas.getMoedasSuportadas().size(); i++) {
                int iterador = 1+i;
                opcoes.add(i, "Opcao["+ iterador +"] "  + moedasSuportadas.getMoedasSuportadas().get(i).getFirst());
            }

            this.opcoes.stream().forEach(System.out::println);
        } catch (FileNotFoundException e){
            System.out.println("Erro ao popular moedas. " + e);
        }
    }

    public void printMoedas(){
        Gson gson = new Gson();

        try {
            JsonReader jr = new JsonReader(new FileReader("moedas.json"));
            Moedas moedasSuportadas = gson.fromJson(jr, Moedas.class);

            moedasSuportadas.getMoedasSuportadas()
                    .forEach( moeda -> System.out.println(moeda.get(1) + "\nCódigo: " + moeda.get(0)
                            )
                    );
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao encontrar arquivo Json, já rodou a classe Persistor? stacktrace:" + e);
        }
    }

    public static void main(String[] args) {
        Moedas moedas = new Moedas();
        moedas.popularOpcoes();
    }
}
