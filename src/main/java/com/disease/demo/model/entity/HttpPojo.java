package com.disease.demo.model.entity;

import lombok.Data;

/**
 * @Auther: Bob
 * @Date: 2020/2/5 14:57
 * @Description: 爬虫请求参数pojo
 */
@Data
public class HttpPojo {
    
    private String httpHost;

    private String httpAccept;

    private String httpConnection;

    private String httpUserAgent;

    private String httpReferer;

    private String httpOrigin;

}
