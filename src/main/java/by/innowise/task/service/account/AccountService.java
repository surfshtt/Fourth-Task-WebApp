package by.innowise.task.service.account;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.model.UserModel;

import java.util.List;

public interface AccountService {
    ApplicationModel getApplication(String username) throws ServiceException;
    UserModel getUser(String username) throws ServiceException;
    List<ApplicationModel> getApplications() throws ServiceException;
}