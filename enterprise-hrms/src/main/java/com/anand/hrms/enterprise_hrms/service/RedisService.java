package com.anand.hrms.enterprise_hrms.service;

public interface RedisService {
    void set(String key, Object value, long timeout);
    <T> T get(String key, Class<T> clazz);
    void delete(String key);
}
