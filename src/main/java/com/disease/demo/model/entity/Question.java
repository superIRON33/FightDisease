package com.disease.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 问题表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    
    private String id;
    
    private String name;
    
    private String aOption;
    
    private String bOption;
    
    private String cOption;
    
    private String dOption;
    
    private String answer;
    
    private Timestamp gmtCreate;
    
    private Timestamp gmtModified;
    
    private Integer deleteStatus;
}
