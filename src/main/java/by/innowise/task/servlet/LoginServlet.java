package by.innowise.task.servlet;

import java.io.*;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.service.auth.impl.AuthenticationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "LoginServlet", value = Constant.LOGIN_PAGE_REDIRECT)
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private final AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(Constant.LOGIN_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(Constant.USER_NAME_PARAMETER);
        String password = request.getParameter(Constant.PASSWORD_PARAMETER);

        try {
            if (authenticationService.tryLogin(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(Constant.USER_NAME_ATTRIBUTE, username);
                session.setAttribute(Constant.IS_LOG_ATTRIBUTE, true);
                logger.info("SERVLET: User '{}' logged in successfully", username);
                response.sendRedirect(request.getContextPath() + Constant.MAIN_PAGE_REDIRECT);
            } else {
                logger.info("SERVLET: Login failed for user '{}'", username);
                request.setAttribute(Constant.ERROR_MESSAGE_ATTRIBUTE, "Incorrect username or password");
                request.getRequestDispatcher(Constant.LOGIN_PAGE).forward(request, response);
            }
        } catch (ServiceException e) {
            logger.warn("SERVLET: Error during login for user '{}'", username, e);
            request.getRequestDispatcher(Constant.ERROR_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}