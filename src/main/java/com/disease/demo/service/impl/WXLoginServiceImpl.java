package com.disease.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.common.enums.WechatEnum;
import com.disease.demo.common.utils.HttpClientUtil;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.User;
import com.disease.demo.service.WXLoginService;
import com.disease.demo.service.base.RedisOperator;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @Auther: Bob
 * @Date: 2020/2/4 09:01
 * @Description: 微信登录服务层实现类
 */
@Service
@Slf4j
public class WXLoginServiceImpl implements WXLoginService {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultDTO login(String code, String name, String avatar) {

        Map<String, String> param = new HashMap<>();
        param.put("appid", WechatEnum.APP_ID.getValue());
        param.put("secret", WechatEnum.SECRET.getValue());
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        //向微信官方服务器发起请求以获得session_key和openid
        String wxResult = HttpClientUtil.doGet(WechatEnum.WX_LOGIN.getValue(), param);
        log.info("wxResult:{}", wxResult);
        //对Json数据进行解析
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        String openid = jsonObject.getString("openid");
        String session_key = jsonObject.getString("session_key");
        String session = UUID.randomUUID().toString().replace("-", " ").toUpperCase();
        if (StringUtil.isNullOrEmpty(openid)) {
            log.error("errcode:{}，errmsg: {}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            return new ResultDTO(ResultEnum.INTERFACE_FAIL);
        }
        Map<String, Object> res = new HashMap<>();
        Optional<User> user = userMapper.findUserByOpenid(openid);
        if (user.isPresent()) {
            //用户已经登陆过
            userMapper.updateUser(user.get().getId(), name, avatar);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("id", user.get().getId());
        } else {
            //用户未登录过
            User newUser = new User(openid, name, avatar);
            userMapper.insertNewUser(newUser);
            redisOperator.set(session, session_key, VariableEnum.LOGIN_TIMEOUT.getValue());
            res.put("id", newUser.getId());
        }
        res.put("session", session);
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        resultDTO.setData(res);
        return resultDTO;
    }
}
