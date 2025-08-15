import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Moedas {
    @SerializedName("supported_codes")
    private List<List<String>> moedasSuportadas;

    public List<List<String>> getMoedasSuportadas() {
        return moedasSuportadas;
    }

    public void printMoedas(){
        Gson gson = new Gson();

        try {
            JsonReader jr = new JsonReader(new FileReader("moedas.json"));
            Moedas moedasSuportadas = gson.fromJson(jr, Moedas.class);

            moedasSuportadas.getMoedasSuportadas()
                    .stream()
                    .forEach( moeda -> System.out.println(moeda.get(1) + "\nCódigo: " + moeda.get(0)
                            )
                    );
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao encontrar arquivo Json, já rodou a classe Persistor? stacktrace:" + e);
        }
    }
}
