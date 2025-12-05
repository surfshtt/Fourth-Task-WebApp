package by.innowise.task.servlet;

public final class ServletConstants {
    public static final String USER_NAME_ATTRIBUTE = "username";
    public static final String IS_LOG_ATTRIBUTE = "isLog";
    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    static final String USER_NAME_PARAMETER = "username";
    static final String PASSWORD_PARAMETER = "password";
    static final String EMAIL_PARAMETER = "email";

    static final String MAIN_PAGE = "index.jsp";
    static final String LOGIN_PAGE = "/pages/login.jsp";
    static final String REG_PAGE = "/pages/registration.jsp";
    static final String ERROR_PAGE = "/pages/error.jsp";

    public static final String MAIN_PAGE_REDIRECT = "/main";
    public static final String LOGIN_PAGE_REDIRECT = "/login";

    private ServletConstants() {}
}
