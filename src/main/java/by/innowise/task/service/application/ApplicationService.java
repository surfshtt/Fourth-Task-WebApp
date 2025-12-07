package by.innowise.task.service.application;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;

public interface ApplicationService {
    void saveQuestionnaire(ApplicationModel application, String userName) throws ServiceException;
}
