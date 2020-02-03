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
 * @description: 问答表服务层实现类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Headquarter")
public class Headquarter {
    
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id")
    @GenericGenerator(name = "snowflake_id", strategy = "com.cstm.demo.common.utils.JpaIdGenUtil")
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "Question")
    private String question;
    
    @Column(name = "answer")
    private String answer;
    
    @Column(name = "gmt_create")
    private Timestamp gmtCreate;
    
    @Column(name = "gmt_modified")
    private Timestamp gmtModified;
    
    @Column(name = "delete_status")
    private Integer deleteStatus;
}
