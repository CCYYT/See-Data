//package com.example.mqtt_springBoot_demo.service.imp;
//
//import com.example.mqtt_springBoot_demo.service.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RedisServiceImp implements RedisService {
//    @Autowired
//    private RedisTemplate redisTemplate;
//    private ValueOperations valueOperations = redisTemplate.opsForValue();
//
//
//    @Override
//    public Object getData(String key) {
//        return valueOperations.get(key);
//    }
//
//    @Override
//    public String addStringKV(String k, String v) {
//        valueOperations.set(k,v);
//        return null;
//    }
//}
