package com.disease.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @Auther: wjy
 * @Date: 2019/11/14 12:31
 * @Description: 服务端向微信小程序发起https请求的工具类
 * CloseableHttpClient是HttpClient接口的实现类
 */
@Slf4j
public class HttpClientUtil {

	/**
	 * 响应状态码
	 */
	private static final Integer RESPONSE_STATUS = 200;

    /**
     * 功能描述: 无参数的GET请求
     *
     * @param: [url]
     * @return: java.lang.String
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 功能描述: 有参数的GET请求
     *
     * @param: [url, param]
     * @return: java.lang.String
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public static String doGet(String url, Map<String, String> param) {
        
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // GET请求的返回内容
        String resultString = "";

        try {
            URIBuilder builder = new URIBuilder(url);
            // 设置请求参数
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            // 构建一个URI对象
            URI uri = builder.build();
            // 创建GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行GET请求
            response = httpClient.execute(httpGet);
            // 获取响应状态码
            if (response.getStatusLine().getStatusCode() == RESPONSE_STATUS) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            log.error("GET Method Catch Exception Handler:{}, message:{}", e, e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * 功能描述: 有参数(Json字符串)的POST请求
     *
     * @param: [url, jsonData]
     * @return: java.lang.String
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public static String doPost1(String url, String jsonData) {
        
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // POST请求的返回内容
        String resultString = "";

        try {
            // 创建POST请求
            HttpPost httpPost = new HttpPost(url);
            // 解析Json数据并构造实体
            StringEntity stringEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            // 执行POST请求
            response = httpClient.execute(httpPost);
            // 获取响应状态码
            if (response.getStatusLine().getStatusCode() == RESPONSE_STATUS) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            log.error("POST Method Catch Exception Handler:{}, message:{}", e, e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * 功能描述: 有参数(Json数据格式)的POST请求(获取微信二维码)
     *
     * @param: [url, param]
     * @return: java.io.InputStream
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    public static InputStream doPost2(String url, String param) {
        
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // POST请求的返回内容
        InputStream resultInputStream = null;

        try {
            // 创建POST请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            // 构造实体
            StringEntity entity = new StringEntity(param);
            entity.setContentType("image/jpeg");
            httpPost.setEntity(entity);
            // 执行POST请求
            response = httpClient.execute(httpPost);
            // 获取响应状态码
            if (response.getStatusLine().getStatusCode() == RESPONSE_STATUS) {
                resultInputStream = response.getEntity().getContent();
                log.info(String.valueOf(response.getEntity()));
            }
        } catch (IOException e) {
            log.error("POST Method Catch Exception Handler:{}, message:{}", e, e.getMessage());
        }
        // 这里不能关闭，为了上传图片。
        
        return resultInputStream;
    }
}