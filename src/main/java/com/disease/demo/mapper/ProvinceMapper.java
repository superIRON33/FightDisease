package com.disease.demo.mapper;

import com.disease.demo.model.entity.City;
import com.disease.demo.model.entity.Province;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: Bob
 * @Date: 2020/2/8 23:33
 * @Description: 省份DAO映射
 */
@Repository
public interface ProvinceMapper {
    /**
     * 功能描述: 获取全国所有省份感染人数
     *
     * @param: []
     * @return: List<Province>
     * @auther: zbw
     * @date: 23:28 2020/2/8
     */
    @Select("SELECT * FROM province")
    List<Province> getAllProviceCount();

    @Select("SELECT * FROM province WHERE name = #{name}")
    Optional<Province> getProvince(@Param("name") String name);

    @Update("UPDATE province SET count = #{count} WHERE name = #{name}")
    Integer updateCount(@Param("name") String name, @Param("count") String count);

    @Insert("INSERT INTO province(name, count) VALUES(#{name}, #{count})")
    Integer addProvince(Province province);
}
