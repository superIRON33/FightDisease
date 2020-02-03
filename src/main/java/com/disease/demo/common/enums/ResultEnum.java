package com.disease.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: zbw
 * @Date: 2020/2/3 12:31
 * @Description: 异常返回枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    
    /**
     * 正常返回(官方)
     */
    SUCCESS(200, "正常返回"),
    
    /**
     * 微信接口请求失败
     */
    INTERFACE_FAIL(20000, "微信接口请求失败"),
    
    /**
     * 用户id无效
     */
    ID_INVALID(20001, "用户id无效"),
    
    /**
     * 今日积分已达上限
     */
    UPPER_LIMIT(20002, "今日积分已达上限"),
    
    /**
     * 城市不存在
     */
    CITY_INVALID(20003, "城市不存在");
    
    private Integer code;
    
    private String message;
}