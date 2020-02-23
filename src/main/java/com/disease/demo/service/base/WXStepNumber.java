package com.disease.demo.service.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.utils.AESUtil;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.HomeDTO;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/6 13:33
 * @description: 微信获取用户今日步数
 */
@Service
public class WXStepNumber {
    
    @Autowired
    private RedisOperator redisOperator;
    
    @Autowired
    private UserMapper userMapper;
    
    public ResultDTO getStepNumber(Optional<User> user, String encryptedData, String iv, String session, Integer integral) {

        String sessionKey = redisOperator.getValue(session);
//        System.out.println("s:" + sessionKey);
//        System.out.println("e:" + encryptedData);
//        System.out.println("iv:" + iv);
        try {
            byte[] resultByte = AESUtil.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(sessionKey),
                    Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(userInfo);
                JSONArray stepArray = jsonObject.getJSONArray("stepInfoList");
                JSONObject today = (JSONObject) stepArray.get(30);
                Integer stepNumber = today.getInteger("step");
                userMapper.updateStepNumber(user.get().getId(), stepNumber);
                HomeDTO homeDTO = new HomeDTO(stepNumber, integral);
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(homeDTO);
                return resultDTO;
            }
            else {
                ResultDTO resultDTO = new ResultDTO(ResultEnum.DECRYPTION_FAIL);
                resultDTO.setData(new HomeDTO(0, integral));
                return resultDTO;
            }
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
