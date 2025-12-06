package by.innowise.task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "QuestionnaireServlet", value = ServletConstants.QUESTIONNAIRE_PAGE_REDIRECT)
public class QuestionnaireServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession currentSession = request.getSession();
        boolean isLogged = Boolean.TRUE.equals(currentSession.getAttribute(ServletConstants.IS_LOG_ATTRIBUTE));

        if(isLogged){
            String speciality = request.getParameter(ServletConstants.SPECIALITY_ATTRIBUTE);
            request.setAttribute(ServletConstants.SPECIALITY_ATTRIBUTE, speciality);
            request.getRequestDispatcher(ServletConstants.QUESTIONNAIRE_PAGE).forward(request,response);
        }
        else{
            request.getRequestDispatcher(ServletConstants.LOGIN_PAGE).forward(request,response);
        }
    }
}
