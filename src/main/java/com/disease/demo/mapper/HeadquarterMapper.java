package com.disease.demo.mapper;

import com.disease.demo.model.entity.Headquarter;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wjy
 * @date: 2020/2/4
 * @description: 问答dao映射
 */
@Repository
public interface HeadquarterMapper {
    
    /**
     * 功能描述: 查询用户是否是第一次登录
     *
     * @param: []
     * @return: java.util.List<com.disease.demo.model.entity.Headquarter>
     * @auther: wjy
     * @date: 2020/2/4 18:00
     */
    @Select("SELECT * FROM headquarter")
    List<Headquarter> findAll();
}
