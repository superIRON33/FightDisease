package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: Bob
 * @Date: 2020/2/9 00:02
 * @Description: 省份DTO
 */
@Data
@AllArgsConstructor
public class ProvinceDTO {

    private String provinceName;
    private String count;
}

