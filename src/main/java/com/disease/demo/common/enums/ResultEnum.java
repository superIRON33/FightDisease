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
    CITY_INVALID(20003, "城市不存在"),
    
    /**
     * 解密失败
     */
    DECRYPTION_FAIL(20004, "解密失败"),
    
    /**
     * 更新失败
     */
    UPDATE_FAIL(20005, "更新失败"),
    
    /**
     * 删除失败
     */
    DELETE_FAIL(20006, "删除失败"),
    
    /**
     * 积分不足
     */
    INTEGRAL_NOT_ENOUGH(20007, "积分不足"),
    
    /**
     * 房间号不存在
     */
    ROOM_NUMBER_IS_INVALID(20008, "房间号不存在"),
    
    /**
     * 没有返回结果
     */
    NO_RETURN(20009, "没有返回结果"),
    
    /**
     * 没有权限
     */
    NO_POWER(20010, "没有权限");
    
    private Integer code;
    
    private String message;
}