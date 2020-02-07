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
import org.apache.ibatis.annotations.Param;
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
                // 这里改成通过uuid获取，防止房间号重复。
                String roomNumber = RandomNumberUtil.getNumber(6);
                Map<String, String> map = new HashMap<>();
                map.put("A", id.toString());
                redisOperator.set(roomNumber, map.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(roomNumber);
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
                    String string = redisOperator.getValue(roomNumber);
                    Map<String, String> toMap = JSONObject.fromObject(string);
                    toMap.put("B", id.toString());
                    redisOperator.set(roomNumber, toMap.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
                    Map<String, String> map = new HashMap<>();
                    map.put("name", user.get().getName());
                    map.put("avatar", user.get().getAvatar());
                    ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                    resultDTO.setData(map);
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
                Map<String, String> toMap = JSONObject.fromObject(s);
                if (toMap.get("A").equals(id)) {
                    toMap.put("Acount", count.toString());
                }
                else {
                    toMap.put("Bcount", count.toString());
                }
                redisOperator.set(roomNumber, toMap.toString(), VariableEnum.ROOM_TIMEOUT.getValue());
    
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
    
            }
            return new ResultDTO(ResultEnum.ROOM_NUMBER_IS_INVALID);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
