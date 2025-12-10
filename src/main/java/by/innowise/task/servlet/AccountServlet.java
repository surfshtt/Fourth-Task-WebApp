package by.innowise.task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.innowise.task.servlet.QuestionnaireServlet.isLogged;

@WebServlet(name = "AccountServlet", value = Constant.ACCOUNT_PAGE_REDIRECT)
public class AccountServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLogged(request)) {
            request.getRequestDispatcher(request.getContextPath() + Constant.LOGIN_PAGE).forward(request, response);
        } else {
            //TODO
        }
    }
}
