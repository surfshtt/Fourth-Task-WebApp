package by.innowise.task.service.account.impl;

import by.innowise.task.dao.impl.ApplicationDao;
import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.account.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();

    private final UserDao userDao = new UserDao();
    private final ApplicationDao applicationDao = new ApplicationDao();

    @Override
    public ApplicationModel getApplication(String username) throws ServiceException {
        logger.info("SERVICE: Loading application for user '{}'", username);
        UserModel user;

        try {
            user = userDao.findByName(username);
        } catch (DaoException e) {
            logger.warn("SERVICE: Failed to load user '{}'", username, e);
            throw new ServiceException(e.getMessage());
        }

        if (user == null) {
            logger.info("SERVICE: User '{}' not found, application is absent", username);
            return null;
        }

        ApplicationModel application;
        try {
            application = applicationDao.findByUserId(user.getId());
            logger.info("SERVICE: Application lookup finished for user '{}'", username);
        } catch (DaoException e) {
            logger.warn("SERVICE: Failed to load application for user '{}'", username, e);
            throw new ServiceException(e.getMessage());
        }
        return application;
    }

    @Override
    public UserModel getUser(String username) throws ServiceException {
        logger.info("SERVICE: Loading user '{}'", username);
        UserModel user;

        try {
            user = userDao.findByName(username);
        } catch (DaoException e) {
            logger.warn("SERVICE: Failed to load user '{}'", username, e);
            throw new ServiceException(e.getMessage());
        }

        return user;
    }

    @Override
    public List<ApplicationModel> getApplications() throws ServiceException {
        logger.info("SERVICE: Loading applications");
        List<ApplicationModel> applications;

        try{
            applications = applicationDao.findAll();
        } catch (DaoException e) {
            logger.warn("SERVICE: Failed to load applications", e);
            throw new ServiceException(e.getMessage());
        }

        return applications;
    }
}
