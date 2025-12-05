package by.innowise.task.servlet;

import java.io.*;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.user.UserModel;
import by.innowise.task.service.auth.impl.AuthenticationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private final AuthenticationServiceImpl authenticationService  = new AuthenticationServiceImpl();;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(ServletConstants.LOGIN_PAGE).forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(ServletConstants.USER_NAME_PARAMETER);
        String password = request.getParameter(ServletConstants.PASSWORD_PARAMETER);

        try {
            if(authenticationService.tryLogin(username, password)){
                HttpSession session = request.getSession();
                session.setAttribute(ServletConstants.USER_NAME_ATTRIBUTE, username);
                session.setAttribute(ServletConstants.IS_LOG_ATTRIBUTE, true);

                response.sendRedirect(request.getContextPath() + ServletConstants.MAIN_PAGE_REDIRECT);
            }
            else{
                request.setAttribute(ServletConstants.ERROR_MESSAGE_ATTRIBUTE, "Неправильное имя пользователя или пароль.");
                request.getRequestDispatcher(ServletConstants.LOGIN_PAGE).forward(request, response);
            }
        }
        catch (ServiceException e) {
            request.getRequestDispatcher(ServletConstants.ERROR_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}