package com.disease.demo.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: zbw
 * @date: 2019/11/15 20:34
 * @description: 拦截器适配器配置
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    // 注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/**") 表示拦截所有的请求。
        // excludePathPatterns("/kjyq/login") 表示登录接口不拦截。
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/epidemic");
        super.addInterceptors(registry);
    }
}