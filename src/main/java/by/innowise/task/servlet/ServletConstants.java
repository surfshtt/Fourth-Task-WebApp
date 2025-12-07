package by.innowise.task.servlet;

public final class ServletConstants {
    public static final String USER_NAME_ATTRIBUTE = "username";
    public static final String IS_LOG_ATTRIBUTE = "isLog";
    public static final String SPECIALITY_ATTRIBUTE = "speciality";
    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    static final String USER_NAME_PARAMETER = "username";
    static final String PASSWORD_PARAMETER = "password";
    static final String EMAIL_PARAMETER = "email";
    static final String FIO_PARAMETER = "fio";
    static final String DIPLOMA_SCORE_PARAMETER = "diplomaScore";
    static final String DESCRIPTION_PARAMETER = "description";
    static final String MOBILE_PHONE_PARAMETER = "mobilePhone";
    static final String SPECIALITY_PARAMETER = "speciality";

    static final String MAIN_PAGE = "index.jsp";
    static final String LOGIN_PAGE = "/pages/login.jsp";
    static final String REG_PAGE = "/pages/registration.jsp";
    static final String QUESTIONNAIRE_PAGE = "/pages/questionnaire.jsp";
    static final String ALL_GOOD_PAGE = "/pages/all_good.jsp";
    static final String ERROR_PAGE = "/pages/error.jsp";

    public static final String MAIN_PAGE_REDIRECT = "/main";
    public static final String LOGIN_PAGE_REDIRECT = "/login";
    public static final String REG_PAGE_REDIRECT = "/registration";
    public static final String QUESTIONNAIRE_PAGE_REDIRECT = "/questionnaire";


    private ServletConstants() {}
}
