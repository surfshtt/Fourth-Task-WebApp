package by.innowise.task.service;

import by.innowise.task.dao.impl.UserDao;
import by.innowise.task.hasher.PasswordHasher;
import by.innowise.task.model.user.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationService {
    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;

    public AuthenticationService() {
        try {
            userDao = new UserDao();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tryLogin(String username, String password) {
        if(username == null || password == null) {
            return false;
        }

        UserModel user = null;
        try {
            user = userDao.findByName(username);
        }
        catch (Exception e){
            logger.warn("Catch exception while trying to find user with name: {}", username, e);
        }

        if(user == null) {
            return false;
        }

        return PasswordHasher.verifyPassword(password, user.getPassword());
    }

    public boolean tryReg(UserModel user) {
        if(user == null || user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            return false;
        }

        UserModel existingUser = new UserModel();
        try {
            existingUser = userDao.findByName(user.getName());
        }
        catch (Exception e){
            logger.warn("Catch exception while trying to find user with name: {}", user.getName(), e);
            return false;
        }

        if(!(existingUser == null)){
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
