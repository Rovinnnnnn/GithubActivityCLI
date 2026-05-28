import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Activity {
    public static void main(String[] args) {
        IO.println(args.length);
        if (args.length != 1) {
            IO.println("Usage: Java githubActivityCLI <username> ");
            return;
        }
        String username = args[0];
        String github_api = "https://api.github.com/users/" + username + "/events";

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(github_api)).header("Accept", "application/vnd.github+json").GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                IO.println(response.body());
            } else {
                IO.println("Error: " + response.statusCode());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }
}