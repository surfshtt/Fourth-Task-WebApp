package by.innowise.task.servlet;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.auth.AuthenticationService;
import by.innowise.task.service.auth.impl.AuthenticationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = Constant.REG_PAGE_REDIRECT)
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private final AuthenticationService authenticationService = new AuthenticationServiceImpl();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(Constant.REG_PAGE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(Constant.USER_NAME_PARAMETER);
        String email = request.getParameter(Constant.EMAIL_PARAMETER);
        String password = request.getParameter(Constant.PASSWORD_PARAMETER);

        UserModel newUser = new UserModel();
        newUser.setName(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        try {
            if (authenticationService.tryReg(newUser)) {
                HttpSession session = request.getSession();
                session.setAttribute(Constant.USER_NAME_ATTRIBUTE, username);
                session.setAttribute(Constant.IS_LOG_ATTRIBUTE, true);
                logger.info("SERVLET: User '{}' registered successfully", username);
                response.sendRedirect(request.getContextPath() + Constant.MAIN_PAGE_REDIRECT);
            } else {
                logger.info("SERVLET: Registration failed for user '{}'", username);
                request.setAttribute(Constant.ERROR_MESSAGE_ATTRIBUTE, "Username is already taken");
                request.getRequestDispatcher(Constant.REG_PAGE).forward(request, response);
            }
        } catch (ServiceException e) {
            logger.warn("SERVLET: Error during registration for user '{}'", username, e);
            request.getRequestDispatcher(Constant.ERROR_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}
