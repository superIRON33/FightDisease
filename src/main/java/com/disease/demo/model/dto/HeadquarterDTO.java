package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装Headquarter
 */
@Data
@AllArgsConstructor
public class HeadquarterDTO {
    
    private String question;
    private String answer;
    private String image;
}
