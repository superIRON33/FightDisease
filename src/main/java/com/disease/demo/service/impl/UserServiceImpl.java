package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.HonorDTO;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.User;
import com.disease.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表服务层实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public ResultDTO getUserInfo(Integer id, String encryptedData, String iv, String session) {
        return null;
    }
    
    @Override
    public ResultDTO getEpidemic(String cityName) {
        return null;
    }
    
    @Override
    public ResultDTO updateIntegral(Integer id, Integer integral, Integer mode) {
    
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            Integer nowIntegral = user.get().getIntegral(), singleIntegral = user.get().getSingleIntegral();
            if (mode.equals(VariableEnum.DOUBLE_INTEGRAL.getValue())) {
                Integer status = userMapper.updateIntegral(nowIntegral + integral);
                return status.equals(VariableEnum.OK.getValue())? new ResultDTO(ResultEnum.UPDATE_FAIL): new ResultDTO(ResultEnum.SUCCESS);
            }
            else if (mode.equals(VariableEnum.SINGLE_INTEGRAL.getValue())) {
                if (singleIntegral >= VariableEnum.UPPER_LIMIT.getValue()) {
                    return new ResultDTO(ResultEnum.UPPER_LIMIT);
                }
                else {
                    Integer status1 = userMapper.updateIntegral(nowIntegral + integral);
                    Integer status2 = userMapper.updateSingleIntegral(singleIntegral + integral);
                    if (status1.equals(VariableEnum.OK.getValue()) || status2.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                    return new ResultDTO(ResultEnum.SUCCESS);
                }
            }
            else if (mode.equals(VariableEnum.LOGIN_INTEGRAL.getValue())) {
                if (user.get().getIntegralLogin().equals(VariableEnum.OK.getValue())) {
                    Integer status = userMapper.updateIntegral(integral + nowIntegral);
                    if (status.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                }
                return new ResultDTO(ResultEnum.SUCCESS);
            }
            else if (mode.equals(VariableEnum.SHARE_INTEGRAL.getValue())) {
                if (user.get().getIntegralShare().equals(VariableEnum.OK.getValue())) {
                    Integer status = userMapper.updateIntegral(integral + nowIntegral);
                    if (status.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                }
                return new ResultDTO(ResultEnum.SUCCESS);
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    
    }
    
    @Override
    public ResultDTO getHonour(Integer id) {
        
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            Integer honoraryTitle = 3, integral = user.get().getIntegral();
            Integer stepNumber = user.get().getStepNumber(), total = userMapper.findCount();
            Float stepNumberRank = (float) userMapper.findStepNumberOver(stepNumber) / total;
            Float integralRank = (float) userMapper.findIntegralOver(integral) / total;
            if (integral < VariableEnum.NO_POISON.getValue()) {
                honoraryTitle = 0;
            }
            else if (integral < VariableEnum.PLAQUE_NEMESIS.getValue()) {
                honoraryTitle = 1;
            }
            else if (integral < VariableEnum.EPIDEMIC_PREVENTION_MASTER.getValue()) {
                honoraryTitle = 3;
            }
            HonorDTO honorDTO = new HonorDTO(user.get().getName(), user.get().getAvatar(),
                    user.get().getDays(), stepNumber, stepNumberRank,
                    integral, integralRank, honoraryTitle);
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(honorDTO);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO updateStatus(Integer id) {
    
        Integer isFirstLogin = userMapper.findIsFirstLogin(id);
        if (isFirstLogin.equals(VariableEnum.DELETE.getValue())) {
            return new ResultDTO(ResultEnum.SUCCESS);
        }
        else if (isFirstLogin.equals(VariableEnum.OK.getValue())) {
            Integer status = userMapper.updateIsFirstLogin(id, VariableEnum.DELETE.getValue());
            if (!status.equals(VariableEnum.OK.getValue())) {
                return new ResultDTO(ResultEnum.SUCCESS);
            }
            else {
                return new ResultDTO(ResultEnum.UPDATE_FAIL);
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
