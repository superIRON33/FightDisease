package com.disease.demo.model.dto;

import com.disease.demo.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: wjy
 * @Date: 2019/11/14 12:31
 * @Description: 封装返回给前端的数据
 */
@Data
@AllArgsConstructor
public class ResultDTO {

    private Integer code;
    private String message;
    private Object data;

    public ResultDTO(ResultEnum result) {
        setResultEnum(result);
    }

    public void setResultEnum(ResultEnum result) {
        
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}