package by.innowise.task.service.account.impl;

import by.innowise.task.dao.impl.ApplicationDao;
import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.ApplicationModel;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.account.AccountService;

import javax.security.auth.login.AccountException;

public class AccountServiceImpl implements AccountService {
    private final UserDao userDao = new UserDao();
    private final ApplicationDao applicationDao = new ApplicationDao();

    @Override
    public ApplicationModel getApplication(String username) throws ServiceException {
        ApplicationModel application = null;
        UserModel user = null;

        try {
            user = userDao.findByName(username);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        long userId = user.getId();
        try {
            application = applicationDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return application;
    }
}
