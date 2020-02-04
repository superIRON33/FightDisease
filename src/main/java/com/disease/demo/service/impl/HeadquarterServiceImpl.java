package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.mapper.HeadquarterMapper;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.HeadquarterDTO;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.Headquarter;
import com.disease.demo.service.HeadquarterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 问答表服务层实体类
 */
@Slf4j
@Service
public class HeadquarterServiceImpl implements HeadquarterService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private HeadquarterMapper headquarterMapper;
    
    @Override
    public ResultDTO getHeadQuarter(Integer id) {
    
        Integer isFirstLogin = userMapper.findIsFirstLogin(id);
        log.info("isFirstLogin", isFirstLogin);
        Map<String, String> map = new HashMap<>();
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        if (isFirstLogin.equals(VariableEnum.DELETE.getValue())) {
            List<Headquarter> headquarters = headquarterMapper.findAll();
            headquarters.forEach(headquarter -> {
                map.put(headquarter.getQuestion(), headquarter.getAnswer());
            });
        }
        else {
            return new ResultDTO(ResultEnum.ID_INVALID);
        }
        resultDTO.setData(new HeadquarterDTO(isFirstLogin, map));
        return resultDTO;
    }
}
