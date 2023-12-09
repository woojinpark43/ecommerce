package com.example.ecommerce.user.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class PasswordUtils {
    public static boolean equalPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
