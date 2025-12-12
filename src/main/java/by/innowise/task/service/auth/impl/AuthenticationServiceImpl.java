package by.innowise.task.service.auth.impl;

import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.exception.ServiceException;
import by.innowise.task.util.PasswordHasher;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.auth.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger();

    private final UserDao userDao = new UserDao();

    @Override
    public boolean tryLogin(String username, String password) throws ServiceException {
        if (username == null || password == null) {
            logger.info("SERVICE: Login rejected because username or password is null");
            return false;
        }

        UserModel user = null;
        try {
            user = userDao.findByName(username);
        } catch (DaoException e) {
            logger.warn("Catch exception while trying to find user with name: {}", username, e);
            throw new ServiceException("Catch exception while trying to find user with name: " + username, e);
        }

        if (user == null) {
            logger.info("SERVICE: Login failed, user '{}' not found", username);
            return false;
        }

        boolean verified = PasswordHasher.verifyPassword(password, user.getPassword());
        if (verified) {
            logger.info("SERVICE: User '{}' successfully authenticated", username);
        } else {
            logger.info("SERVICE: User '{}' failed authentication", username);
        }
        return verified;
    }

    @Override
    public boolean tryReg(UserModel user) throws ServiceException {
        if (user == null || user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            logger.info("SERVICE: Registration rejected because required fields are missing");
            return false;
        }

        UserModel existingUser = null;
        try {
            existingUser = userDao.findByName(user.getName());
        } catch (DaoException e) {
            logger.warn("Catch exception while trying to find user with name: {}", user.getName(), e);
            throw new ServiceException("Catch exception while trying to find user with name: " + user.getName(), e);
        }

        if (existingUser != null) {
            logger.info("SERVICE: Registration failed because user '{}' already exists", user.getName());
            return false;
        }

        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        user.setRole(UserModel.Role.APPLICANT);

        try {
            userDao.insert(user);
        } catch (Exception e) {
            logger.warn("Catch exception while trying to insert user: {}", user, e);
            return false;
        }

        logger.info("SERVICE: User '{}' successfully registered", user.getName());
        return true;
    }
}
