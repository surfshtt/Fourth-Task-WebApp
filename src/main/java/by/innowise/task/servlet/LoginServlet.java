package by.innowise.task.servlet;

import java.io.*;

import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.model.user.UserModel;
import by.innowise.task.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private AuthenticationService authenticationService;

    public void init() {
        authenticationService = new AuthenticationService();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        UserModel user = new UserModel();
//        user.setName("123");
//        user.setPassword("123");
//        user.setEmail("23@gmail.com");
//        user.setRole(UserModel.Role.ADMIN);
//        UserDao userDao = null;
//        try {
//            userDao = new UserDao();
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            userDao.insert(user);
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }

        //response.sendRedirect("/");
    }

    public void destroy() {
    }
}