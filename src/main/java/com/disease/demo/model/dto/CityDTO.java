package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装City
 */
@Data
@AllArgsConstructor
public class CityDTO {
    
    private String cityName;
    private String count;
}
