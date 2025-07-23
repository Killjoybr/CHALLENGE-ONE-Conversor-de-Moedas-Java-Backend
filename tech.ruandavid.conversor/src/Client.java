import com.sun.net.httpserver.Request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Client {
    private String url;

    private HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
    // private HttpResponse<String> response;
    // Abaixo vou aproveitar o exemplo assincrono do javadocs para a classe http
    private HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.ofMinutes(2))
            .header("Content-Type", "application/json")
            .build();

    public Client(String url) {
        this.url = url;
    }

    public static void main(String[] args) {
        //Client client = new Client("https://viacep.com.br/ws/01001000/json");
        Client client = new Client("https://viacep.com.br/ws/01001000/json");

        client.client.sendAsync(client.request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
                .join();
    }
}