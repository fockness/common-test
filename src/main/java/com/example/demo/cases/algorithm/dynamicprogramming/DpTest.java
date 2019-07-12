package com.example.demo.cases.algorithm.dynamicprogramming;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class DpTest {

    @Test
    public void testArray(){
        List<Integer> list = Lists.newArrayList(1,2,4,1,7,8,3);
        System.out.println(recOpt(list, 6));
    }

    /**
     * 找出不相邻数字能凑出的最大数
     * @param list
     * @param position
     * @return
     */
    private Integer recOpt(List<Integer> list, Integer position){
        //递归出口
        if(position == 0){
            return list.get(0);
        }
        if(position == 1){
            return Integer.max(list.get(0), list.get(1));
        }
        return Integer.max(recOpt(list, position-2) + list.get(position)
                , recOpt(list,position - 1));
    }

    @Test
    public void testSubset(){
        List<Integer> list = Lists.newArrayList(3, 34, 4, 12, 5, 2);
        System.out.println(subset(list, 5, 9));
    }

    /**
     * 找出是否能用给定数组凑出给定数字
     * @param list
     * @param position
     * @param target
     * @return
     */
    private Boolean subset(List<Integer> list, Integer position, Integer target){
        //递归出口
        if(target == 0){
            return true;
        }
        if(position == 0){
            return list.get(position).equals(target);
        }
        if(list.get(position) > target){
            return subset(list, position-1, target);
        }
        return subset(list, position-1, target - list.get(position))
                || subset(list, position - 1, target);
    }
}

