package com.disease.demo.common.interceptor;


import com.disease.demo.service.base.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zbw
 * @date: 2019/11/15 20:34
 * @description: 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String session = request.getHeader("session");
        if (session.equals("")) {
            return false;
        } else {
            if (redisOperator.hasKey(session)) {
                return true;
            }
            return false;
        }
    }
}
