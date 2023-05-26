package hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

/*
* http://localhost:8080/test
* */
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("TestServlet.servlet");
        res.getWriter().println("test");
    }
}
