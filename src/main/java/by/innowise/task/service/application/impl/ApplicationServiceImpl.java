package by.innowise.task.service.application.impl;

import by.innowise.task.dao.impl.ApplicationDao;
import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.application.ApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger logger = LogManager.getLogger();

    private final UserDao userDao = new UserDao();
    private final ApplicationDao applicationDao = new ApplicationDao();

    public void saveQuestionnaire(ApplicationModel application, String userName) throws ServiceException {
        logger.info("SERVICE: Saving questionnaire for user '{}'", userName);
        UserModel user;

        try {
            user = userDao.findByName(userName);
        } catch (DaoException e) {
            logger.warn("Catch exception while trying to find user with name: {}", userName);
            throw new ServiceException(e.getMessage());
        }

        application.setUserId(user.getId());
        application.setStatus(ApplicationModel.Status.SUBMITTED);

        try {
            applicationDao.insert(application);
            logger.info("SERVICE: Questionnaire stored for user '{}'", userName);
        } catch (DaoException e) {
            logger.warn("Catch exception while trying to insert application: {}", application);
            throw new ServiceException(e.getMessage());
        }
    }
}
