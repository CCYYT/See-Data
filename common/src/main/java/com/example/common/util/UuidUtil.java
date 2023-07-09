package com.example.common.util;

import org.springframework.util.AlternativeJdkIdGenerator;

public class UuidUtil {
    public static AlternativeJdkIdGenerator a = new AlternativeJdkIdGenerator();

    public static String getUuid(){
        return a.generateId().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
    }
}
