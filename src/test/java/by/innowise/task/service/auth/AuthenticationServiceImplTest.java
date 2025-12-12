package by.innowise.task.service.auth;

import by.innowise.task.exception.ServiceException;
import by.innowise.task.model.UserModel;
import by.innowise.task.service.auth.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AuthenticationServiceImplTest {

    private final AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

    @Test
    void tryLoginReturnsFalseWhenUsernameIsNull() throws ServiceException {
        assertFalse(authenticationService.tryLogin(null, "password"));
    }

    @Test
    void tryLoginReturnsFalseWhenPasswordIsNull() throws ServiceException {
        assertFalse(authenticationService.tryLogin("username", null));
    }

    @Test
    void tryRegReturnsFalseWhenRequiredFieldsMissing() throws ServiceException {
        UserModel incompleteUser = new UserModel();
        incompleteUser.setName("testUser");

        assertFalse(authenticationService.tryReg(incompleteUser));
    }
}

