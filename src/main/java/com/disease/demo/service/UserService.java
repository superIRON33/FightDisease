package com.disease.demo.service;

import com.disease.demo.model.dto.ResultDTO;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表服务层
 */
public interface UserService {
    
    ResultDTO getUserInfo(String id);
    
    ResultDTO getEpidemic(String cityName);
    
    ResultDTO updateIntegral(String id, Integer integral, Integer mode);
    
    ResultDTO getHonour(String id);
    
    ResultDTO updateStatus(String id);
}
