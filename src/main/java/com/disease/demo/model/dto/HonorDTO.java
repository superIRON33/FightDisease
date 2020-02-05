package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装生成荣誉页接口的数据
 */
@Data
@AllArgsConstructor
public class HonorDTO {
    
    private String name;
    private String avatar;
    private Integer days;
    private Integer stepNumber;
    private Integer stepNumberRank;
    private Integer integral;
    private Integer integralRank;
    private Integer honoraryTitle;
}
