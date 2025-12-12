package by.innowise.task.servlet;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.service.account.AccountService;
import by.innowise.task.service.account.impl.AccountServiceImpl;
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

    private final AccountServiceImpl accountService = new AccountServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLogged(request)) {
            log.info("SERVLET: Unauthenticated access to account page, redirecting to login");
            request.getRequestDispatcher(request.getContextPath() + Constant.LOGIN_PAGE).forward(request, response);
        } else {
            ApplicationModel applictaion;
            try {
                applictaion = accountService.getApplication(request.getParameter(Constant.USER_NAME_PARAMETER));
            } catch (ServiceException e) {
                log.warn("SERVLET: Failed to load account data", e);
                request.getRequestDispatcher(Constant.ERROR_PAGE).forward(request, response);
            }
            //TODO
        }
    }
}
