package com.disease.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wjy
 * @Date: 2019/11/14 12:31
 * @Description: 项目常量值枚举类
 */
@Getter
@AllArgsConstructor
public enum VariableEnum {

    /**
     * id无效
     */
    INVALID(-1),

    /**
     * 未删除/请求成功/用户第一次登录/设默认值
     */
    OK(0),

    /**
     * 已删除/用户非第一次登录
     */
    DELETE(1),
    
    /**
     * 用户初始积分
     */
    INITIAL_INTEGRAL(20),
    
    /**
     * 通过登录获得积分
     */
    LOGIN_INTEGRAL(1),
    
    /**
     * 通过分享荣誉获得积分
     */
    SHARE_INTEGRAL(2),
    
    /**
     * 通过单人答题获得积分
     */
    SINGLE_INTEGRAL(3),
    
    /**
     * 通过双人答题获得积分
     */
    DOUBLE_INTEGRAL(4),
    
    /**
     * 每日积分上限
     */
    UPPER_LIMIT(50),
    
    /**
     * 百毒不侵(步数小于100)
     */
    NO_POISON(100),
    
    /**
     * 瘟疫克星(步数100-1000)
     */
    PLAQUE_NEMESIS(1000),
    
    /**
     * 防疫大师(步数1000-10000)
     */
    EPIDEMIC_PREVENTION_MASTER(10000),
    
    /**
     * 登录超时时间-发布版(7天、单位为秒)
     */
    LOGIN_TIMEOUT(7 * 24 * 60 * 60),
    
    /**
     * 登录超时时间-测试版(2分钟、单位为秒)
     */
    LOGIN_TIMEOUT_TEST(2 * 60);

    private Integer value;
}