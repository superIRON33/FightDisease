package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.common.utils.RandomNumberUtil;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.User;
import com.disease.demo.service.DoubleQuestionService;
import com.disease.demo.service.base.RedisOperator;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/7 23:12
 * @description: 双人答题服务层实现类
 */
@Service
public class DoubleQuestionServiceImpl implements DoubleQuestionService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RedisOperator redisOperator;
    
    @Override
    public ResultDTO buildRoom(Integer id) {
    
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            if (user.get().getIntegral() >= VariableEnum.INTEGRAL_LOWER_LIMIT.getValue()) {
                String roomNumber = RandomNumberUtil.getCharacter(6);
                redisOperator.set(roomNumber, "", VariableEnum.ROOM_TIMEOUT.getValue());
                Map<String, String> map = new HashMap<>();
                map.put("roomNumber", roomNumber);
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(map);
                return resultDTO;
            }
            return new ResultDTO(ResultEnum.INTEGRAL_NOT_ENOUGH);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO enterRoom(Integer id, String roomNumber) {
    
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            if (user.get().getIntegral() >= VariableEnum.INTEGRAL_LOWER_LIMIT.getValue()) {
                if (redisOperator.hasKey(roomNumber)) {
                    String b_roomNumber = RandomNumberUtil.getCharacter(6);
                    JSONObject json1 = new JSONObject();
                    JSONObject json2 = new JSONObject();
                    json1.put("roomNumber", roomNumber);
                    json2.put("roomNumber", b_roomNumber);
                    redisOperator.set(b_roomNumber, json1.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
                    redisOperator.set(roomNumber, json2.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
                    Map<String, String> returnMap = new HashMap<>();
                    returnMap.put("name", user.get().getName());
                    returnMap.put("avatar", user.get().getAvatar());
                    returnMap.put("roomNumber", b_roomNumber);
                    ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                    resultDTO.setData(returnMap);
                    return resultDTO;
                }
                return new ResultDTO(ResultEnum.ROOM_NUMBER_IS_INVALID);
            }
            return new ResultDTO(ResultEnum.INTEGRAL_NOT_ENOUGH);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO result(Integer id, String roomNumber, Integer count) {
    
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            if (redisOperator.hasKey(roomNumber)) {
                String s = redisOperator.getValue(roomNumber);
                JSONObject jsonObject1 = JSONObject.fromObject(s);
                jsonObject1.put("count", count.toString());
                redisOperator.set(roomNumber, jsonObject1.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
                if (!redisOperator.hasKey((String) jsonObject1.get("roomNumber"))) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (redisOperator.hasKey((String) jsonObject1.get("roomNumber"))) {
                    int i = 0;
                    while (!(JSONObject.fromObject(redisOperator.getValue((String) jsonObject1.get("roomNumber")))).containsKey("count") && i < 5) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    JSONObject jsonObject2 = JSONObject.fromObject(redisOperator.getValue((String) jsonObject1.get("roomNumber")));
                    if (jsonObject2.has("count")) {
                        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                        resultDTO.setData(jsonObject2.get("count"));
                        return resultDTO;
                    }
                    else {
                        return new ResultDTO(ResultEnum.NO_RETURN);
                    }
                }
                return new ResultDTO(ResultEnum.ROOM_NUMBER_IS_INVALID);
            }
            return new ResultDTO(ResultEnum.ROOM_NUMBER_IS_INVALID);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
