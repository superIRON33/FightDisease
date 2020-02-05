package com.disease.demo.mapper;

import com.disease.demo.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
     * @return: User
     * @auther: zbw
     * @date: 11:41 2020/2/4
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<User> findUserById(@Param("id") Integer id);

    /**
     * 功能描述: 插入用户信息
     *
     * @param: [name, avatar]
     * @return: void
     * @auther: zbw
     * @date: 11:42 2020/2/4
     */

    @Insert("INSERT INTO user(name, avatar) VALUES(#{name}, #{avatar})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertNewUser(User user);

    /**
     * 功能描述: 更新指定用户信息
     *
     * @param: [id, name, avatar]
     * @return: [void]
     * @auther: zbw
     * @date: 11:42 2020/2/4
     */
    @Update("UPDATE user SET name = #{name}, avatar = #{avatar} WHERE id = #{id}")
    Integer updateUser(@Param("id") Integer id, @Param("name") String name, @Param("avatar") String avatar);
    
    /**
     * 功能描述: 查询用户是否是第一次登录
     *
     * @param: [id]
     * @return: [Integer]
     * @auther: wjy
     * @date: 2020/2/4 16:41
     */
    @Select("SELECT is_first_login FROM user WHERE id = #{id}")
    Integer findIsFirstLogin(@Param("id") Integer id);
    
    @Update("UPDATE user SET is_first_login = #{is_first_login} WHERE id = #{id}")
    Integer updateIsFirstLogin(@Param("id") Integer id, @Param("is_first_login") Integer isFirstLogin);
    
    /**
     * 功能描述: 查询当前用户数量
     *
     * @param: []
     * @return: java.util.List<java.lang.Integer>
     * @auther: wjy
     * @date: 2020/2/4 20:25
     */
    @Select("SELECT COUNT(id) FROM user")
    Integer findCount();
    
    /**
     * 功能描述: 查看步数低于多少用户
     *
     * @param: [stepNumber]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/4 20:30
     */
    @Select("SELECT COUNT(id) FROM user WHERE step_number > #{step_number}")
    Integer findStepNumberOver(@Param("step_number") Integer stepNumber);

    /**
     * 功能描述: 查看积分高于多少用户
     *
     * @param: [integral]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/4 20:30
     */
    @Select("SELECT COUNT(id) FROM user WHERE integral < #{integral}")
    Integer findIntegralOver(@Param("integral") Integer integral);
    
    /**
     * 功能描述: 更新用户积分
     *
     * @param: [integral]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/4 20:46
     */
    @Select("UPDATE user SET integral = #{integral}")
    Integer updateIntegral(@Param("integral") Integer integral);
    
    /**
     * 功能描述: 更新用户单人游戏积分
     *
     * @param: [single_integral]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/4 21:03
     */
    @Select("UPDATE user SET single_integral = #{single_integral}")
    Integer updateSingleIntegral(@Param("single_integral") Integer single_integral);
}
