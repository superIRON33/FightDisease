package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装总部页接口的数据
 */
@Data
@AllArgsConstructor
public class HeadquarterDTO {
    
    private Integer isFirst;
    private Map<String, String> choiceQuestion;
}
