package by.innowise.task.servlet;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.account.AccountService;
import by.innowise.task.service.account.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static by.innowise.task.servlet.QuestionnaireServlet.isLogged;

@WebServlet(name = "AccountServlet", value = Constant.ACCOUNT_PAGE_REDIRECT)
public class AccountServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    private final AccountServiceImpl accountService = new AccountServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isLogged(request)) {
            log.info("SERVLET: Unauthenticated access to account page, redirecting to login");
            request.getRequestDispatcher(Constant.LOGIN_PAGE).forward(request, response);
        } else {
            ApplicationModel application = null;
            UserModel user = null;
            try {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute(Constant.USER_NAME_ATTRIBUTE);
                application = accountService.getApplication(username);
                user = accountService.getUser(username);
            } catch (ServiceException e) {
                log.warn("SERVLET: Failed to load account data", e);
                request.getRequestDispatcher(Constant.ERROR_PAGE).forward(request, response);
            }

            request.setAttribute(Constant.APPLICATION_ATTRIBUTE, application);
            request.setAttribute(Constant.USER_ATTRIBUTE, user);

            if (user.getRole() == UserModel.Role.ADMIN) {
                try {
                    List<ApplicationModel> applications = accountService.getApplications();
                    request.setAttribute(Constant.APPLICATIONS_ATTRIBUTE, applications);
                } catch (ServiceException e) {
                    request.setAttribute(Constant.APPLICATIONS_ATTRIBUTE, null);
                    log.warn("SERVLET: Failed to load applications data for ADMIN", e);
                }
            }

            request.getRequestDispatcher(Constant.ACCOUNT_PAGE).forward(request, response);
        }
    }
}
