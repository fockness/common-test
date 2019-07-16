package com.example.demo.cases.algorithm.greedy;

import lombok.Data;
import org.junit.Test;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-8 10:15
 * 4
 */
public class GreedyTest {

    @Test
    public void testGreedy(){
        /**
         * 趣学算法加勒比海盗船
         * 载重固定为c，wi越小，可装载的数量n越大，问求尽可能多的装载n的算法（数量优先）
         *
         * 4，10，7，11，3，5，14，2
         *
         *
         */
        Integer[] values = {4,10,7,11,3,5,14,2};//每个单位的负重
        Integer load = 30;//负重30
        reedy1(values, load);
    }

    private void reedy1(Integer[] values, Integer load){
        //先升序排序
        for(int i=0; i<values.length; i++){
            for(int j=0; j<values.length-i-1; j++){
                if(values[j] > values[j+1]){
                    Integer temp = values[j];
                    values[j] = values[j+1];
                    values[j+1] = temp;
                }
            }
        }
        Integer sum = 0;//带走几件
        for(Integer value : values){
            load -= value;
            if(load >= 0){
                sum++;
                System.out.print(value + ",");
            }
        }
        System.out.println("total:" + sum);
    }

    //===============================================================================
    @Test
    public void testGreedy2(){
        /**
         *趣学算法阿里巴巴与四十大盗
         * 怎样使用有限的负载运走最大财富（单位价值有限）
         *
         * 宝物编号i     2    10    6    3    5    8    9    4    7    1
         * 重量wi        2    5    8    9    5     4    5    5    5    4
         * 价值vi       8     15   20   18   8     6    7    6    5    3
         * 性价比pi     4     3    2.5   2   1.6   1.5 1.4   1.2  1   0.75
         */

    }

    private void greedy2(Integer values[], Integer load){

    }

    @Data
    private class treasure{
        private Integer num;//宝物编号
        private Integer weight;//宝物重量
        private Integer value;//宝物价值
        private Integer unit;//单位价值
    }
}
