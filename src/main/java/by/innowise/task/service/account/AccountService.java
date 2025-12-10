package by.innowise.task.service.account;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;

public interface AccountService {
    ApplicationModel getApplication(String username) throws ServiceException;
}