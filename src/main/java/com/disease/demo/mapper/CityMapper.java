package com.disease.demo.mapper;

import com.disease.demo.model.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: Bob
 * @Date: 2020/2/6 10:13
 * @Description: 城市DAO映射
 */
@Mapper
@Repository
public interface CityMapper {

    @Update("UPDATE city SET count = #{count} WHERE name = #{name}")
    Integer updateCount(@Param("name") String name, @Param("count") String count);

    @Select("SELECT * FROM city WHERE name = #{name}")
    Optional<City> getCount(@Param("name") String name);

    @Insert("INSERT INTO city(name, count) VALUES(#{name}, #{count})")
    Integer addCity(City city);
}
