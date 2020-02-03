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
     * id无效
     */
    ID_INVALID(20001, "id无效");

    
    private Integer code;
    private String message;
}