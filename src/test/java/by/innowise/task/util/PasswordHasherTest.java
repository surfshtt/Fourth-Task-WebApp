package by.innowise.task.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordHasherTest {

    @Test
    void hashAndVerifyPassword() {
        String password = "secret123";

        String hash = PasswordHasher.hashPassword(password);

        assertNotNull(hash);
        assertTrue(PasswordHasher.verifyPassword(password, hash));
    }

    @Test
    void verificationFailsWithWrongPassword() {
        String password = "secret123";
        String hash = PasswordHasher.hashPassword(password);

        assertFalse(PasswordHasher.verifyPassword("wrongPassword", hash));
    }

    @Test
    void verificationFailsWithInvalidHash() {
        assertFalse(PasswordHasher.verifyPassword("anyPassword", "invalid-hash"));
    }
}

