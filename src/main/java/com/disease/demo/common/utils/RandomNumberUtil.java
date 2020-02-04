package com.disease.demo.common.utils;

import java.util.Random;

/**
 * @Auther: wjy
 * @Date: 2019/11/18 20:16
 * @Description: 生成n位随机数字
 */
public class RandomNumberUtil {

    /**
     * 功能描述: 生成n位随机数字
     *
     * @param: [n]（需要生成随机数的位数）
     * @return: String
     * @auther: wjy
     * @date: 20:05 2019/11/18
     */
    public static String getNumber(int n) {
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int number = new Random().nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
}
