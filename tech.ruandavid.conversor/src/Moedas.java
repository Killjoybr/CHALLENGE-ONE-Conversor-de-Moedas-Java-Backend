import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Moedas {
    @SerializedName("supported_codes")
    private List<List<String>> moedasSuportadas;

    public List<List<String>> getMoedasSuportadas() {
        return moedasSuportadas;
    }
}
