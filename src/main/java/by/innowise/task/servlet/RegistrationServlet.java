package by.innowise.task.servlet;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.user.UserModel;
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

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private final AuthenticationService authenticationService = new AuthenticationServiceImpl();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages/registration.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(ServletConstants.USER_NAME_PARAMETER);
        String email = request.getParameter(ServletConstants.EMAIL_PARAMETER);
        String password = request.getParameter(ServletConstants.PASSWORD_PARAMETER);

        UserModel newUser = new UserModel();
        newUser.setName(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        try {
            if(authenticationService.tryReg(newUser)){
                HttpSession session = request.getSession();
                session.setAttribute(ServletConstants.USER_NAME_ATTRIBUTE, username);
                session.setAttribute(ServletConstants.IS_LOG_ATTRIBUTE, true);

                response.sendRedirect(request.getContextPath() + ServletConstants.MAIN_PAGE_REDIRECT);
            }
            else{
                request.setAttribute(ServletConstants.ERROR_MESSAGE_ATTRIBUTE, "Имя пользователя занято");
                request.getRequestDispatcher(ServletConstants.REG_PAGE).forward(request, response);
            }
        }
        catch (ServiceException e) {
            request.getRequestDispatcher(ServletConstants.ERROR_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}
