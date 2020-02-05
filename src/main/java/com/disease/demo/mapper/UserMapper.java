package com.disease.demo.mapper;

import com.disease.demo.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/4
 * @description: 用户dao映射
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> findAll();
    
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
     * @param: [user]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:23
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
    
    /**
     * 功能描述: 更新用户的is_first_login字段
     *
     * @param: [id, isFirstLogin]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 16:31
     */
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
     * 功能描述: 更新单个用户积分
     *
     * @param: [id, integral]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:13
     */
    @Update("UPDATE user SET integral = #{integral} WHERE id = #{id}")
    Integer updateIntegral(@Param("id") Integer id, @Param("integral") Integer integral);
    
    /**
     * 功能描述: 更新用户单人游戏积分
     *
     * @param: [id, single_integral]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:09
     */
    @Update("UPDATE user SET single_integral = #{single_integral} WHERE id = #{id}")
    Integer updateSingleIntegral(@Param("id") Integer id, @Param("single_integral") Integer singleIntegral);
    
    /**
     * 功能描述: 初始化用户单人游戏积分
     *
     * @param: []
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:10
     */
    @Update("UPDATE user SET single_integral = 0")
    Integer initializeSingleIntegral();
    
    /**
     * 功能描述: 更新integralLogin
     *
     * @param: [id]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 19:21
     */
    @Update("UPDATE user SET integral_login = 1 WHERE id = #{id}")
    Integer updateIntegralLogin(@Param("id") Integer id);
    
    /**
     * 功能描述: 初始化integralLogin
     *
     * @param: []
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:03
     */
    @Update("UPDATE user SET integral_login = 0")
    Integer initializeIntegralLogin();
    
    /**
     * 功能描述: 更新integralShare
     *
     * @param: [id]
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 19:21
     */
    @Update("UPDATE user SET integral_share = 1 WHERE id = #{id}")
    Integer updateIntegralShare(@Param("id") Integer id);
    
    /**
     * 功能描述: 初始化integralShare
     *
     * @param: []
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 21:06
     */
    @Update("UPDATE user SET integral_share = 0")
    Integer initializeIntegralShare();
    
    
    /**
     * 功能描述: 初始化今日步数
     *
     * @param: []
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 19:25
     */
    @Update("UPDATE user SET step_number = 0")
    Integer initializeStepNumber();
    
    /**
     * 功能描述: 更新用户登录天数
     *
     * @param: []
     * @return: java.lang.Integer
     * @auther: wjy
     * @date: 2020/2/5 19:40
     */
    @Update("UPDATE user SET days = days + 1 WHERE integral_login = 1")
    Integer updateDays();
}
