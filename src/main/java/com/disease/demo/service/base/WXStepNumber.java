package com.disease.demo.service.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wjy
 * @date: 2020/2/6 13:33
 * @description: 微信获取用户今日步数
 */
@Slf4j
@Service
public class WXStepNumber {
    
    @Autowired
    private RedisOperator redisOperator;
    
    
    public Integer getStepNumber(String encryptedData, String iv, String session) {

//        String result = MyAESUtil.decryptWXAppletInfo(session, encryptedData, iv);
//        log.info(result);
        return 0;
    }
}
