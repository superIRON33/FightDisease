package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装获取用户步数和积分接口的数据
 */
@Data
@AllArgsConstructor
public class StepNumberAndIntegralDTO {
    
    private Integer stepNumber;
    private Integer integral;
}
