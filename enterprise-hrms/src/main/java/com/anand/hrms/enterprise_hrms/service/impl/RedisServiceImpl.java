package com.anand.hrms.enterprise_hrms.service.impl;

import com.anand.hrms.enterprise_hrms.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, Object value, long timeout){
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key, Class<T> clazz){
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj == null)return null;

        return clazz.cast(obj);
    }

    @Override
    public void delete(String key){
        redisTemplate.delete(key);
    }
}
