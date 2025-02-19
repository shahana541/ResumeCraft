import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ResumeServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/submit", new ResumeHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080...");
    }

    static class ResumeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String requestBody = new String(exchange.getRequestBody().readAllBytes());
                Map<String, String> formData = parseFormData(requestBody);

                String resumeHTML = "<html><head><title>Generated Resume</title></head><body>";
                resumeHTML += "<h1 style='color:blue;'>" + formData.get("name") + "</h1>";
                resumeHTML += "<p><strong>Email:</strong> " + formData.get("email") + "</p>";
                resumeHTML += "<p><strong>Phone:</strong> " + formData.get("phone") + "</p>";
                resumeHTML += "<h2>Education</h2><p>" + formData.get("education") + "</p>";
                resumeHTML += "<h2>Experience</h2><p>" + formData.get("experience") + "</p>";
                resumeHTML += "<h2>Skills</h2><p>" + formData.get("skills") + "</p>";
                resumeHTML += "<h2>Projects</h2><p>" + formData.get("projects") + "</p>";
                resumeHTML += "<button onclick='window.close()'>Close</button>";
                resumeHTML += "</body></html>";

                exchange.sendResponseHeaders(200, resumeHTML.length());
                OutputStream os = exchange.getResponseBody();
                os.write(resumeHTML.getBytes());
                os.close();
            }
        }

        private Map<String, String> parseFormData(String formData) throws IOException {
            Map<String, String> map = new HashMap<>();
            for (String pair : formData.split("&")) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    map.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
                }
            }
            return map;
        }
    }
}
