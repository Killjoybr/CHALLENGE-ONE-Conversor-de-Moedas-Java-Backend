import com.sun.net.httpserver.Request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Client {
    private final HttpClient client;
    private final HttpRequest request;

    // private HttpResponse<String> response;
    // Abaixo vou aproveitar o exemplo assincrono do javadocs para a classe http

    public Client(String url) {
        this.client = HttpClient.newBuilder().build();

        try {
            this.request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .build();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Url invalida: " +url, e);
        }
    }

    public String sendRequest(){
        return client.sendAsync(this.request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }

    public static void main(String[] args) {
        Client client = new Client("https://viacep.com.br/ws/01001000/json/");

        String output = client.sendRequest();

        System.out.println(output);
    }
}