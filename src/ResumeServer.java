import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ResumeServer {
    public static void main(String[] args) throws IOException {
        // Create HTTP Server on Port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Define route for handling form submission
        server.createContext("/generateResume", new ResumeHandler());

        // Start the server
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on http://localhost:8000");
    }

    // Handle resume generation request
    static class ResumeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read request body
                Scanner scanner = new Scanner(exchange.getRequestBody(), StandardCharsets.UTF_8.name());
                StringBuilder requestData = new StringBuilder();
                while (scanner.hasNextLine()) {
                    requestData.append(scanner.nextLine());
                }
                scanner.close();

                // Process form data
                String response = generateResume(requestData.toString());

                // Send response back to frontend
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    // Function to format resume as HTML
    private static String generateResume(String requestData) {
        String[] fields = requestData.split("&");
        StringBuilder resume = new StringBuilder("<html><head><title>Resume Preview</title></head><body>");

        for (String field : fields) {
            String[] keyValue = field.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].replace("+", " ");
                String value = keyValue[1].replace("+", " ");
                resume.append("<h3>").append(key).append("</h3><p>").append(value).append("</p>");
            }
        }

        resume.append("</body></html>");
        return resume.toString();
    }
}
