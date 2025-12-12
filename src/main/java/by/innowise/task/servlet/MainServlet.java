package by.innowise.task.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "MainServlet", value = Constant.MAIN_PAGE_REDIRECT)
public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("SERVLET: Forwarding to main page");
        request.getRequestDispatcher(Constant.MAIN_PAGE).forward(request, response);
    }
}
