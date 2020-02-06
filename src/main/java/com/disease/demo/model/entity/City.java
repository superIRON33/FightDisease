package com.disease.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: Bob
 * @Date: 2020/2/6 10:11
 * @Description: 城市表实体类
 */
@Data
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private String count;

    public City(String name, String count) {
        this.name = name;
        this.count = count;
    }
}
