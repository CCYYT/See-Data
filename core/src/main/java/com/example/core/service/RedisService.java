package com.example.core.service;

public interface RedisService {
    public Object getData(String key);

    public String addStringKV(String k,String v);


}
