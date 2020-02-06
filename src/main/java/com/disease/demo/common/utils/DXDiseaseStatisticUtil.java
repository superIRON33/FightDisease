package com.disease.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.disease.demo.model.entity.HttpPojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Bob
 * @Date: 2020/2/5 15:22
 * @Description: 爬取工具类
 */
public class DXDiseaseStatisticUtil {

    /**
     * 功能描述: 对map进行解析
     *
     * @param: paramObj
     * @return: String
     * @auther: zbw
     * @date: 15:24 2020/2/5
     */
    public static String parseParam(Map paramObj){
        
        String param = "";
        if (paramObj != null && !paramObj.isEmpty()){
            for (Object key:paramObj.keySet()){
                String value = paramObj.get(key).toString();
                param += (key + "=" + value + "&");

            }
        }
        return param;
    }

    /**
     * 功能描述: 伪造ip地址
     *
     * @param: []
     * @return: String
     * @auther: zbw
     * @date: 15:25 2020/2/5
     */
    public static String randIP() {
        
        Random random = new Random(System.currentTimeMillis());
        return (random.nextInt(255) + 1) + "." + (random.nextInt(255) + 1)
                + "." + (random.nextInt(255) + 1) + "."
                + (random.nextInt(255) + 1);
    }

    /**
     * 功能描述: 对目标爬取网站发起Http请求
     *
     * @param: [url, paramObj, httpPojo]
     * @return: String
     * @auther: zbw
     * @date: 15:30 2020/2/5
     */
    public static String httpSendGet(String url, Map paramObj, HttpPojo httpPojo){
        
        String result = "";
        String urlName = url + "?" + parseParam(paramObj);
        BufferedReader in=null;
        try {

            URL realURL = new URL(urlName);
            URLConnection conn = realURL.openConnection();
            //伪造ip访问
            String ip = randIP();
            System.out.println("目前伪造的ip："+ip);
            conn.setRequestProperty("X-Forwarded-For", ip);
            conn.setRequestProperty("HTTP_X_FORWARDED_FOR", ip);
            conn.setRequestProperty("HTTP_CLIENT_IP", ip);
            conn.setRequestProperty("REMOTE_ADDR", ip);
            conn.setRequestProperty("Host", httpPojo.getHttpHost());
            conn.setRequestProperty("accept", httpPojo.getHttpAccept());
            conn.setRequestProperty("connection", httpPojo.getHttpConnection());
            conn.setRequestProperty("user-agent", httpPojo.getHttpUserAgent());
            conn.setRequestProperty("Referer",httpPojo.getHttpReferer()); //伪造访问来源
            conn.setRequestProperty("Origin", httpPojo.getHttpOrigin()); //伪造访问域名
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                //System.out.println(s + "-->" + map.get(s));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in!=null){
                try {
                    in.close();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    public static String getAreaStat(){
        
        String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";
        //模拟请求
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //整个html页面
        //System.out.println(htmlResult);

        //正则获取数据
        //因为html的数据格式看着就像json格式，所以我们正则获取json
        String reg= "window.getAreaStat = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
        String result="";
        if (totalMatcher.find()){
            result = totalMatcher.group(1);
            //System.out.println(result);
            //各个省市的是一个列表List，如果想保存到数据库中，要遍历结果，下面是demo
        }
        return result;
    }
}
