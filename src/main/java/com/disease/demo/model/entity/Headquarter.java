package com.disease.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 问答表服务层实现类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Headquarter {
    
    private String id;
    
    private String question;
    
    private String answer;
    
    private Timestamp gmtCreate;
    
    private Timestamp gmtModified;
    
    private Integer deleteStatus;
}
