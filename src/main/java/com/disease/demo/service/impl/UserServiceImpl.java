package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.mapper.CityMapper;
import com.disease.demo.mapper.ProvinceMapper;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.*;
import com.disease.demo.model.entity.City;
import com.disease.demo.model.entity.Province;
import com.disease.demo.model.entity.User;
import com.disease.demo.service.UserService;
import com.disease.demo.service.base.WXStepNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表服务层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private WXStepNumber wxStepNumber;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public ResultDTO getUserInfo(Integer id, String encryptedData, String iv, String session) {
        
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            Integer integral = user.get().getIntegral(), newStepNumber = 0;
            if (encryptedData != null && iv != null && session != null) {
                return wxStepNumber.getStepNumber(user, encryptedData, iv, session, integral);
            }
            else {
                HomeDTO homeDTO = new HomeDTO(newStepNumber, integral);
                ResultDTO resultDTO = new ResultDTO(ResultEnum.NO_POWER);
                resultDTO.setData(homeDTO);
                return resultDTO;
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }

    @Override
    public ResultDTO getEpidemic(String cityName) {
        
        Optional<City> city = cityMapper.findCity("%" + cityName + "%");
        if (city.isPresent()) {
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(new CityDTO(city.get().getName(), city.get().getCount()));
            return resultDTO;
        } else {
            return new ResultDTO(ResultEnum.CITY_INVALID);
        }
    }
    
    @Override
    public ResultDTO updateIntegral(Integer id, Integer integral, Integer mode) {
    
        Optional<User> user = userMapper.findUserById(id);
        if (user.isPresent()) {
            Integer nowIntegral = user.get().getIntegral(), singleIntegral = user.get().getSingleIntegral();
            if (mode.equals(VariableEnum.DOUBLE_INTEGRAL.getValue())) {
                Integer status = userMapper.updateIntegral(id, nowIntegral + integral);
                return status.equals(VariableEnum.OK.getValue())? new ResultDTO(ResultEnum.UPDATE_FAIL): new ResultDTO(ResultEnum.SUCCESS);
            }
            else if (mode.equals(VariableEnum.SINGLE_INTEGRAL.getValue())) {
                if (singleIntegral + integral >= VariableEnum.UPPER_LIMIT.getValue()) {
                    Integer status1 = userMapper.updateIntegral(user.get().getId(), nowIntegral + VariableEnum.UPPER_LIMIT.getValue() - singleIntegral);
                    Integer status2 = userMapper.updateSingleIntegral(user.get().getId(), VariableEnum.UPPER_LIMIT.getValue());
                    if (status1.equals(VariableEnum.OK.getValue()) || status2.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                    return new ResultDTO(ResultEnum.UPPER_LIMIT);
                }
                else {
                    Integer status1 = userMapper.updateIntegral(user.get().getId(), nowIntegral + integral);
                    Integer status2 = userMapper.updateSingleIntegral(user.get().getId(), singleIntegral + integral);
                    if (status1.equals(VariableEnum.OK.getValue()) || status2.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                    return new ResultDTO(ResultEnum.SUCCESS);
                }
            }
            else if (mode.equals(VariableEnum.LOGIN_INTEGRAL.getValue())) {
                if (user.get().getIntegralLogin().equals(VariableEnum.OK.getValue())) {
                    Integer status1 = userMapper.updateIntegral(user.get().getId(), integral + nowIntegral);
                    Integer status2 = userMapper.updateIntegralLogin(user.get().getId());
                    if (status1.equals(VariableEnum.OK.getValue()) || status2.equals(VariableEnum.OK.getValue())) {
                        return new ResultDTO(ResultEnum.UPDATE_FAIL);
                    }
                }
                return new ResultDTO(ResultEnum.SUCCESS);
            }
            else if (mode.equals(VariableEnum.SHARE_INTEGRAL.getValue())) {
                if (user.get().getIntegralShare().equals(VariableEnum.OK.getValue())) {
                    Integer status1 = userMapper.updateIntegral(user.get().getId(), integral + nowIntegral);
                    Integer status2 = userMapper.updateIntegralShare(user.get().getId());
                    if (status1.equals(VariableEnum.OK.getValue()) || status2.equals(VariableEnum.OK.getValue())) {
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
            Integer stepNumber = user.get().getStepNumber(), total = userMapper.findCount() - 1;
            Integer stepNumberRank = (int) ((float) userMapper.findStepNumberOver(stepNumber) * 100 / total);
            Integer integralRank = (int) ((float) userMapper.findIntegralOver(integral)  * 100 / total);
            if (stepNumber < VariableEnum.NO_POISON.getValue()) {
                honoraryTitle = 0;
            }
            else if (stepNumber < VariableEnum.PLAQUE_NEMESIS.getValue()) {
                honoraryTitle = 1;
            }
            else if (stepNumber < VariableEnum.EPIDEMIC_PREVENTION_MASTER.getValue()) {
                honoraryTitle = 2;
            }
            HonorDTO honorDTO = new HonorDTO(user.get().getName(), user.get().getAvatar(),
                    user.get().getDays(), stepNumber, stepNumberRank,
                    integral, integralRank, honoraryTitle);
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(honorDTO);
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO updateStatus(Integer id) {
    
        Integer isFirstLogin = userMapper.findIsFirstLogin(id);
        if (isFirstLogin != null) {
            if (isFirstLogin.equals(VariableEnum.DELETE.getValue())) {
                return new ResultDTO(ResultEnum.SUCCESS);
            } else if (isFirstLogin.equals(VariableEnum.OK.getValue())) {
                Integer status = userMapper.updateIsFirstLogin(id, VariableEnum.DELETE.getValue());
                if (!status.equals(VariableEnum.OK.getValue())) {
                    return new ResultDTO(ResultEnum.SUCCESS);
                } else {
                    return new ResultDTO(ResultEnum.UPDATE_FAIL);
                }
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }

    @Override
    public ResultDTO getAllProvince() {
        List<Province> list = provinceMapper.getAllProviceCount();
        List<ProvinceDTO> provinces = new ArrayList<>();
        if (list.size() != 0) {
            list.forEach(province -> {
                ProvinceDTO provinceDTO = new ProvinceDTO(province.getName(), province.getCount());
                provinces.add(provinceDTO);
            });
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(provinces);
            return resultDTO;
        } else {
            return new ResultDTO(ResultEnum.NO_RETURN);
        }
    }
}
