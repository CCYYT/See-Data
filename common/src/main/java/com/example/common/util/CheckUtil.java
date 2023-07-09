package com.example.common.util;

import java.util.regex.Pattern;

public class CheckUtil {

    public static boolean checkIsEmail(String email){
        return Pattern
                .compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}+$")
                .matcher(email)
                .matches();
    }

    public static boolean checkIsVerificationCode(String code){
        return Pattern
                .compile("^[0-9a-zA-Z]{6}$")
                .matcher(code)
                .matches();
    }
}
