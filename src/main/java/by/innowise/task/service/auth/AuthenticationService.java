package by.innowise.task.service.auth;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.user.UserModel;

public interface AuthenticationService {
    boolean tryLogin(String username, String password) throws ServiceException;
    boolean tryReg(UserModel user) throws ServiceException;
}
