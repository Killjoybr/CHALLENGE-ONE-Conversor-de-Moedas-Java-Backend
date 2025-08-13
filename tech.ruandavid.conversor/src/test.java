import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class test {

    public static void main(String[] args) throws FileNotFoundException {
        JsonReader jr = new JsonReader(new FileReader("moedas.json"));
        Gson gson = new Gson();

        Moedas moedasSuportadas = gson.fromJson(jr, Moedas.class);

        moedasSuportadas.getMoedasSuportadas().forEach(System.out::println);
    }
}
