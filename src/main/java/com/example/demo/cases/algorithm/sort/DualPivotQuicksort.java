package com.example.demo.cases.algorithm.sort;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-17 15:59
 * 4    双路快排
 *      array[left+1.....i-1]<base
 *      array[j........right]>base
 *      有问题
 */
public class DualPivotQuicksort {

    @Test
    public void testTwoWayQuickSort(){
        Integer[] array = {2,3,5,1,7,3,5,23,12,75,22,1,0};
        recursive(array, 0, array.length-1);
        CollectionUtil.print(array);
    }

    private void recursive(Integer[] array, Integer left, Integer right){
        if(left >= right){
            return;
        }
        Integer base = sort(array, left, right);
        recursive(array, left, base-1);
        recursive(array, base+1, right);
    }

    private Integer sort(Integer[] array, Integer left, Integer right){
        Integer base = array[left];
        Integer j = right;
        Integer i = left + 1;
        while(true){
            while(i<=right && array[i]<base) i++;
            while(j>=left+1 && array[j]>base) j--;
            if(i>j){
                break;
            }
            CollectionUtil.swap(array, i++, j--);
        }
        CollectionUtil.swap(array, left, j);
        return j;
    }
}
