package com.disease.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 封装总部页接口的数据
 */
@Data
@AllArgsConstructor
public class HeadquarterListDTO {
    
    private Integer isFirst;
    private List<HeadquarterDTO> choiceQuestion;
}
