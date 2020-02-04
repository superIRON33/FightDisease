package com.disease.demo.service.impl;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表服务层实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public ResultDTO getUserInfo(String id, String encryptedData, String iv, String session) {
        return null;
    }
    
    @Override
    public ResultDTO getEpidemic(String cityName) {
        return null;
    }
    
    @Override
    public ResultDTO updateIntegral(String id, Integer integral, Integer mode) {
        return null;
    }
    
    @Override
    public ResultDTO getHonour(String id) {
        return null;
    }
    
    @Override
    public ResultDTO updateStatus(String id) {
        return null;
    }
}
