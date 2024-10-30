package org.htblaleonding.jonasfroeller;

import org.htblaleonding.jonasfroeller.utility.PasswordUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilsUnitTest {
    @Test
    public void testHashPassword() {
        String plainPassword = "password";
        String hashedPassword = PasswordUtils.hashPassword(plainPassword);

        // Ensure that the hashed password is not null and not empty
        assertNotNull(hashedPassword);
        assertFalse(hashedPassword.isEmpty());

        // Ensure that the hashed password does not match the plain password
        assertNotEquals(plainPassword, hashedPassword);
    }

    @Test
    public void testCheckPassword_Valid() {
        String plainPassword = "password";
        String hashedPassword = PasswordUtils.hashPassword(plainPassword);

        // Check that the plain password matches the hashed password
        assertTrue(PasswordUtils.checkPassword(plainPassword, hashedPassword));
    }

    @Test
    public void testCheckPassword_Invalid() {
        String plainPassword = "password";
        String wrongPassword = "wrongPassword";
        String hashedPassword = PasswordUtils.hashPassword(plainPassword);

        // Check that a wrong password does not match the hashed password
        assertFalse(PasswordUtils.checkPassword(wrongPassword, hashedPassword));
    }
}
