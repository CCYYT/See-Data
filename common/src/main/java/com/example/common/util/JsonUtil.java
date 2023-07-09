package com.example.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JackSon 序列化工具类
 * */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String ObjectToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    public static Object JsonToObject(String json,Class clazz) throws JsonProcessingException {
        return objectMapper.readValue(json,clazz);
    }

    /**
     * 将对象装换为字节数组
     * */
    public static byte[] convertToBytes(Object object) throws Exception {
        return objectMapper.writeValueAsBytes(object);
    }

    /**
     * 将字节数组装换为对象
     * */
    public static Object bytesToObject(byte[] bytes,Class clazz) throws Exception {
        return objectMapper.readValue(bytes, clazz);
    }
}
