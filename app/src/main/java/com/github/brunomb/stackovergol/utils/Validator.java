package com.github.brunomb.stackovergol.utils;

/**
 * Created by brunomb on 3/11/2017
 */

public class Validator {
    private static final String INVALID_NAME_MESSAGE = "Invalid name";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";

    public static String validateEmail(String email) {
        boolean isValid = !(email == null || "".equals(email) || email.split("").length >= 50);

        if(isValid) {
            return null;
        } else {
            return INVALID_NAME_MESSAGE;
        }
    }

    public static String validatePassword(String password) {
        boolean isValid = !(password.split("").length <= 4);

        if(isValid) {
            return null;
        } else {
            return INVALID_PASSWORD_MESSAGE;
        }
    }
}
