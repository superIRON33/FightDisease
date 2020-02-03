package com.disease.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 问题表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Question")
public class Question {
    
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id")
    @GenericGenerator(name = "snowflake_id", strategy = "com.cstm.demo.common.utils.JpaIdGenUtil")
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "a_option")
    private String aOption;
    
    @Column(name = "b_option")
    private String bOption;
    
    @Column(name = "c_option")
    private String cOption;
    
    @Column(name = "d_option")
    private String dOption;
    
    @Column(name = "answer")
    private String answer;
    
    @Column(name = "gmt_create")
    private Timestamp gmtCreate;
    
    @Column(name = "gmt_modified")
    private Timestamp gmtModified;
    
    @Column(name = "delete_status")
    private Integer deleteStatus;
}
