package backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/generateResume")
public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String education = request.getParameter("education");
        String experience = request.getParameter("experience");
        String skills = request.getParameter("skills");
        String projects = request.getParameter("projects");

        out.println("<html><head><title>Resume Preview</title></head><body>");
        out.println("<h2>Resume Preview</h2>");
        out.println("<h3>" + name + "</h3>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Phone:</strong> " + phone + "</p>");
        out.println("<p><strong>Education:</strong> " + education + "</p>");
        out.println("<p><strong>Experience:</strong> " + experience + "</p>");
        out.println("<p><strong>Skills:</strong> " + skills + "</p>");
        out.println("<p><strong>Projects:</strong> " + projects + "</p>");
        out.println("</body></html>");
    }
}
