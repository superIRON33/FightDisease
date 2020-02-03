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
     * 未删除/请求成功/用户第一次登录
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
     * 每日积分上限
     */
    UPPER_LIMIT(50),
    
    /**
     * 百毒不侵(步数小于100)
     */
    NO_POISON(0),
    
    /**
     * 瘟疫克星(步数100-1000)
     */
    PLAQUE_NEMESIS(1),
    
    /**
     * 防疫大师(步数1000-10000)
     */
    EPIDEMIC_PREVENTION_MASTER(2),
    
    /**
     * 抗疫勇士(步数超过10000)
     */
    ANTI_EPIDEMIC_WARRIOR(3),
    
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