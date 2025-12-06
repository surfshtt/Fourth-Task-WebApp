package by.innowise.task.service.auth.impl;

import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.exception.ServiceException;
import by.innowise.task.hasher.PasswordHasher;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.auth.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;

    public AuthenticationServiceImpl() {
        try {
            userDao = new UserDao();
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public boolean tryLogin(String username, String password) throws ServiceException{
        if(username == null || password == null) {
            return false;
        }

        UserModel user = null;
        try {
            user = userDao.findByName(username);
        }
        catch (DaoException e){
            logger.warn("Catch exception while trying to find user with name: {}", username, e);
            throw new ServiceException("Catch exception while trying to find user with name: " + username, e);
        }

        if(user == null) {
            return false;
        }

        return PasswordHasher.verifyPassword(password, user.getPassword());
    }

    @Override
    public boolean tryReg(UserModel user) throws ServiceException {
        if(user == null || user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            return false;
        }

        UserModel existingUser = null;
        try {
            existingUser = userDao.findByName(user.getName());
        }
        catch (DaoException e){
            logger.warn("Catch exception while trying to find user with name: {}", user.getName(), e);
            throw new ServiceException("Catch exception while trying to find user with name: " + user.getName(), e);
        }

        if(existingUser != null){
            return false;
        }

        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        user.setRole(UserModel.Role.APPLICANT);

        try {
            userDao.insert(user);
        }
        catch (Exception e){
            logger.warn("Catch exception while trying to insert user: {}", user, e);
            return false;
        }

        return true;
    }
}
