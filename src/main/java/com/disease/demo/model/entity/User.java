package com.disease.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private Integer id;
    
    private String name;
    
    private String avatar;
    
    private Integer stepNumber;
    
    private Integer integral;
    
    private Integer integralLogin;
    
    private Integer integralShare;
    
    private Integer singleIntegral;
    
    private Integer isFirstLogin;
    
    private Integer days;

//    public User(String name, String avatar) {
//        this.name = name;
//        this.avatar = avatar;
//    }
}
