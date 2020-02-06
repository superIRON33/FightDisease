package com.disease.demo.service;

import com.disease.demo.model.dto.ResultDTO;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表服务层
 */
public interface UserService {
    
    ResultDTO getUserInfo(Integer id, String encryptedData, String iv, String session);
    
    ResultDTO getEpidemic(String cityName);
    
    ResultDTO updateIntegral(Integer id, Integer integral, Integer mode);
    
    ResultDTO getHonour(Integer id);
    
    ResultDTO updateStatus(Integer id);
}
