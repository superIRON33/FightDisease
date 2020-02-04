package com.disease.demo.mapper;

import com.disease.demo.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/4
 * @description: 用户dao映射
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 功能描述: 通过id查询用户信息
     *
     * @param: [id]
     * @return: Optional<User>
     * @auther: zbw
     * @date: 11:41 2020/2/4
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<User> findUserById(@Param("id") String id);

    /**
     * 功能描述: 插入用户信息
     *
     * @param: [name, avatar]
     * @return: void
     * @auther: zbw
     * @date: 11:42 2020/2/4
     */
    @Insert("INSERT INTO user(name, avatar) VALUES(#{name}, #{avatar})")
    void insertNewUser(@Param("name") String name, @Param("avatar") String avatar);

    /**
     * 功能描述: 更新指定用户信息
     *
     * @param: [id, name, avatar]
     * @return: [void]
     * @auther: zbw
     * @date: 11:42 2020/2/4
     */
    @Update("UPDATE user SET name = #{name}, avatar = #{avatar} WHERE id = #{id}")
    void updateUser(@Param("id") String id, @Param("name") String name, @Param("avatar") String avatar);
}
