package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.mapper.HeadquarterMapper;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.dto.HeadquarterDTO;
import com.disease.demo.model.dto.HeadquarterListDTO;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.Headquarter;
import com.disease.demo.service.HeadquarterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (isFirstLogin != null) {
            List<HeadquarterDTO> headquarterList = new ArrayList<>();
            if (isFirstLogin.equals(VariableEnum.DELETE.getValue())) {
                List<Headquarter>  headquarters = headquarterMapper.findAll();
                headquarters.forEach(headquarter -> {
                    HeadquarterDTO headquarterDTO = new HeadquarterDTO(headquarter.getQuestion(),
                            headquarter.getAnswer(), headquarter.getImage());
                    headquarterList.add(headquarterDTO);
                });
            }
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(new HeadquarterListDTO(isFirstLogin, headquarterList));
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
