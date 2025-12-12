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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "QuestionnaireServlet", value = Constant.QUESTIONNAIRE_PAGE_REDIRECT)
public class QuestionnaireServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private final ApplicationServiceImpl applicationService = new ApplicationServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(isLogged(request)){
            String speciality = request.getParameter(Constant.SPECIALITY_ATTRIBUTE);
            request.setAttribute(Constant.SPECIALITY_ATTRIBUTE, speciality);
            logger.info("SERVLET: Opening questionnaire page for speciality '{}'", speciality);
            request.getRequestDispatcher(Constant.QUESTIONNAIRE_PAGE).forward(request,response);
        }
        else{
            logger.info("SERVLET: Unauthenticated questionnaire access, forwarding to login");
            request.getRequestDispatcher(Constant.LOGIN_PAGE).forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(isLogged(request)){
            String userName = (String) request.getSession().getAttribute(Constant.USER_NAME_ATTRIBUTE);

            ApplicationModel application = new ApplicationModel();

            application.setFio(request.getParameter(Constant.FIO_PARAMETER));
            application.setDiplomaScore(Float.parseFloat(request.getParameter(Constant.DIPLOMA_SCORE_PARAMETER)));
            application.setDescription(request.getParameter(Constant.DESCRIPTION_PARAMETER));
            application.setMobilePhone(request.getParameter(Constant.MOBILE_PHONE_PARAMETER));

            application.setFacultyName((String) request.getAttribute(Constant.SPECIALITY_PARAMETER));

            try {
                applicationService.saveQuestionnaire(application, userName);
                logger.info("SERVLET: Questionnaire submitted by user '{}'", userName);
                request.getRequestDispatcher(Constant.ALL_GOOD_PAGE).forward(request, response);
            }catch(ServiceException e){
                logger.warn("SERVLET: Failed to submit questionnaire for user '{}'", userName, e);
                request.getRequestDispatcher(Constant.ERROR_PAGE).forward(request, response);
            }
        }
        else{
            logger.info("SERVLET: Unauthenticated questionnaire submission, forwarding to login");
            request.getRequestDispatcher(Constant.LOGIN_PAGE).forward(request,response);
        }
    }

    static boolean isLogged(HttpServletRequest request){
        HttpSession currentSession = request.getSession();
        return Boolean.TRUE.equals(currentSession.getAttribute(Constant.IS_LOG_ATTRIBUTE));
    }
}
