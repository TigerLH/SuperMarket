package com.remark.util;

/**
 * @author hong.lin
 * @description
 * @date 2017/1/20.
 */
public class RandomUtil {
    /**
     * 产生指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static Integer getRandomNumber(int min,int max){
        return (int)(Math.random()*(max-min+1)+min);
    }

}
