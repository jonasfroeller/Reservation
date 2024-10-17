package org.htblaleonding.jonasfroeller.utility;

import org.mindrot.jbcrypt.BCrypt;

// https://mvnrepository.com/artifact/org.mindrot/jbcrypt
// alt: https://mvnrepository.com/artifact/at.favre.lib/bcrypt
public class PasswordUtils {
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
