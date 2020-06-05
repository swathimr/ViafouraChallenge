package com.viafoura.utils;

public class StringUtil {

    public static final String INVALID_STRING = "Invalid Input String";
    private static final String regex_check = "[a-zA-Z0-9]+";

    public static boolean isAlphaNumeric(String str) {
        return str.matches(regex_check);
    }
}
