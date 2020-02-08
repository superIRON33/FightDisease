package com.disease.demo.service.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.disease.demo.common.utils.AESUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.security.*;

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

        String sessionKey = redisOperator.getValue(session);
        try {
            byte[] resultByte = AESUtil.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(sessionKey),
                    Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(userInfo);
                JSONArray stepArray = jsonObject.getJSONArray("stepInfoList");
                JSONObject today = (JSONObject) stepArray.get(0);
                Integer stepNumber = today.getInteger("step");
                log.info("今日步数: " + stepNumber.toString());
                return stepNumber;
            }
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
