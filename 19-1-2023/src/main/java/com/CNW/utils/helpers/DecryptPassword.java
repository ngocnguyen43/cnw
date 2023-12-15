package com.CNW.utils.helpers;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class DecryptPassword {
    public static Boolean Decrypt(String password, String Hashed) {
        return BCrypt.checkpw(password, Hashed);
    }
}
