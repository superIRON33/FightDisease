package com.disease.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id")
    @GenericGenerator(name = "snowflake_id", strategy = "com.cstm.demo.common.utils.JpaIdGenUtil")
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "step_number")
    private Integer stepNumber;
    
    @Column(name = "integral")
    private Integer integral;
    
    @Column(name = "integral_login")
    private Integer integralLogin;
    
    @Column(name = "integral_share")
    private Integer integralShare;
    
    @Column(name = "single_integral")
    private Integer singleIntegral;
    
    @Column(name = "is_first_login")
    private Integer isFirstLogin;
    
    @Column(name = "days")
    private Integer days;
}
