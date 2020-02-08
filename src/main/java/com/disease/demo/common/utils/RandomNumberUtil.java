package com.disease.demo.common.utils;

import java.util.Random;

/**
 * @Auther: wjy
 * @Date: 2019/11/18 20:16
 * @Description: 生成n位随机字符
 */
public class RandomNumberUtil {

    /**
     * 功能描述: 生成n位随机字符
     *
     * @param: [n]（需要生成随机字符的位数）
     * @return: String
     * @auther: wjy
     * @date: 20:05 2019/11/18
     */
    public static String getCharacter(int n) {
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c = (char) (new Random().nextInt(10) + 'A');
            sb.append(c);
        }
        return sb.toString();
    }
}
