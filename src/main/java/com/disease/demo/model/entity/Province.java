package com.disease.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: Bob
 * @Date: 2020/2/8 23:29
 * @Description: 省份表实体类
 */
@Data
@NoArgsConstructor
public class Province {

    private Integer id;
    private String name;
    private String count;

    public Province(String name, String count) {
        this.name = name;
        this.count = count;
    }
}
