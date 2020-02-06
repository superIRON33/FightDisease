package com.disease.demo.service;

import com.disease.demo.model.dto.ResultDTO;

/**
 * @Auther: Bob
 * @Date: 2020/2/4 09:00
 * @Description: 微信登录服务层
 */
public interface WXLoginService {

    ResultDTO login(String code, String name, String avatar);
}
