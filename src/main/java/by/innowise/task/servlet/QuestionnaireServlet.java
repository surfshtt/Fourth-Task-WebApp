package by.innowise.task.servlet;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.service.application.impl.ApplicationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "QuestionnaireServlet", value = ServletConstants.QUESTIONNAIRE_PAGE_REDIRECT)
public class QuestionnaireServlet extends HttpServlet {

    private final ApplicationServiceImpl applicationService = new ApplicationServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(isLogged(request)){
            String speciality = request.getParameter(ServletConstants.SPECIALITY_ATTRIBUTE);
            request.setAttribute(ServletConstants.SPECIALITY_ATTRIBUTE, speciality);
            request.getRequestDispatcher(ServletConstants.QUESTIONNAIRE_PAGE).forward(request,response);
        }
        else{
            request.getRequestDispatcher(ServletConstants.LOGIN_PAGE).forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(isLogged(request)){
            String userName = (String) request.getSession().getAttribute(ServletConstants.USER_NAME_ATTRIBUTE);

            ApplicationModel application = new ApplicationModel();

            application.setFio(request.getParameter(ServletConstants.FIO_PARAMETER));
            application.setDiplomaScore(Float.parseFloat(request.getParameter(ServletConstants.DIPLOMA_SCORE_PARAMETER)));
            application.setDescription(request.getParameter(ServletConstants.DESCRIPTION_PARAMETER));
            application.setMobilePhone(request.getParameter(ServletConstants.MOBILE_PHONE_PARAMETER));

            application.setFacultyName((String) request.getAttribute(ServletConstants.SPECIALITY_PARAMETER));

            try {
                applicationService.saveQuestionnaire(application, userName);
                request.getRequestDispatcher(ServletConstants.ALL_GOOD_PAGE).forward(request, response);
            }catch(ServiceException e){
                request.getRequestDispatcher(ServletConstants.ERROR_PAGE).forward(request, response);
            }
        }
        else{
            request.getRequestDispatcher(ServletConstants.LOGIN_PAGE).forward(request,response);
        }
    }

    private boolean isLogged(HttpServletRequest request){
        HttpSession currentSession = request.getSession();
        return Boolean.TRUE.equals(currentSession.getAttribute(ServletConstants.IS_LOG_ATTRIBUTE));
    }
}
