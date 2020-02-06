package com.disease.demo.mapper;

import com.disease.demo.model.entity.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Bob
 * @Date: 2020/2/6 10:13
 * @Description: 城市DAO映射
 */
@Mapper
@Repository
public interface CityMapper {

    @Insert("INSERT INTO city(name, count) VALUES(#{name},#{count})")
    Integer addCity(City city);

    @Select("SELECT count FROM city WHERE name = #{name}")
    Integer getCount(@Param("name") String name);
}
