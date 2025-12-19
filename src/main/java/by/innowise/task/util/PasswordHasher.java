package by.innowise.task.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private static final Logger logger = LogManager.getLogger();

    private static final int WORKLOAD = 10;

    public static String hashPassword(String password) {
        logger.debug("UTIL: Hashing password");
        return BCrypt.hashpw(password, BCrypt.gensalt(WORKLOAD));
    }

    public static boolean verifyPassword(String plainPassword, String storedHash) {
        try {
            boolean result = BCrypt.checkpw(plainPassword, storedHash);
            logger.debug("UTIL: Password verification result: {}", result);
            return result;
        } catch (Exception e) {
            logger.warn("UTIL: Password verification failed due to exception", e);
            return false;
        }
    }
}
