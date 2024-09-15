package com.example.hotel.common;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {
    /***
     * Method to hash the password
     * @param plainPassword
     * @return
     */
    public static String hashPassword(String plainPassword) {
        // Generate salt and hash the password
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    public static boolean checkpw(String plainPassword,String hashedPassword) {
        boolean isMatch = BCrypt.checkpw(plainPassword, hashedPassword);
        return isMatch;
    }
}