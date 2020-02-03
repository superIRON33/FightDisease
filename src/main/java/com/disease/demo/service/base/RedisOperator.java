package com.disease.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wjy
 * @Date: 2019/11/14 12:31
 * @Description: redis的相关操作类
 */
@Service
public class RedisOperator {
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 功能描述: 添加key-value键值对
     *
     * @param: [key, value, timeout]
     * @return: void
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public void set(String key, String value, long timeout) {
        
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    
    /**
     * 功能描述: 查询key是否存在
     *
     * @param: [key]
     * @return: java.lang.Boolean
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public Boolean hasKey(String key) {
        
        return redisTemplate.hasKey(key);
    }
    
    /**
     * 功能描述: 获取key对应的value
     *
     * @param: [key]
     * @return: java.lang.String
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public String getValue(String key) {
        
        return redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 功能描述: 根据key删除key和value
     *
     * @param: [key]
     * @return: void
     * @auther: wjy
     * @date: 2019/11/18 10:33
     */
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
}