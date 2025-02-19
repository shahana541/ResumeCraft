import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

public class ResumeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/submit", (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                handlePostRequest(exchange);
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("✅ Server started on port " + port + "...");
    }

    private static void handlePostRequest(HttpExchange exchange) throws IOException {
        try {
            // Read the request body
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            System.out.println("📩 Received Data: " + requestBody);

            // Convert request body into a map
            Map<String, String> formData = parseFormData(requestBody);

            // Generate a basic resume preview (can be improved)
            String response = "<h1>Resume Preview</h1>" +
                    "<p><strong>Name:</strong> " + formData.getOrDefault("name", "N/A") + "</p>" +
                    "<p><strong>Email:</strong> " + formData.getOrDefault("email", "N/A") + "</p>" +
                    "<p><strong>Phone:</strong> " + formData.getOrDefault("phone", "N/A") + "</p>" +
                    "<p><strong>Education:</strong> " + formData.getOrDefault("education", "N/A") + "</p>" +
                    "<p><strong>Experience:</strong> " + formData.getOrDefault("experience", "N/A") + "</p>";

            // Send the response
            sendResponse(exchange, 200, response);
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error: " + e.getMessage());
        }
    }

    private static Map<String, String> parseFormData(String body) {
        return body.lines()
                .flatMap(line -> java.util.Arrays.stream(line.split("&")))
                .map(pair -> pair.split("="))
                .filter(pair -> pair.length == 2)
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); // Fixes CORS issues
        exchange.sendResponseHeaders(statusCode, response.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
