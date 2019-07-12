package com.example.demo.cases.algorithm.leetcode;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * Leetcode75
 *  sort colors
 */
public class SortColors {

    @Test
    public void testSortColors(){
        Integer[] array = {0, 1, 1, 2, 0, 2, 1, 2};
        sort(array);
        CollectionUtil.print(array);
    }

    /**
     * 计数排序：适合长度长但范围小的数组排序
     * @param array
     */
    private void sort(Integer[] array){
        //桶
        Integer[] count = {0, 0, 0};
        for(Integer integer : array){
            count[integer]++;
        }
        int index = 0;
        for(int i=0; i<count[0];i++){
            array[index++] = 0;
        }
        for(int i=0; i<count[1];i++){
            array[index++] = 1;
        }
        for(int i=0; i<count[2];i++){
            array[index++] = 2;
        }
    }
}
