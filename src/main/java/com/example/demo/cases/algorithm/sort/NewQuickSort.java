package com.example.demo.cases.algorithm.sort;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-16 19:54
 * 4   快速排序
 *      还有问题
 */
public class NewQuickSort {

    @Test
    public void testTwoWayQuickSort(){
        Integer[] array = {2,3,5,1,7,3,8,1,2,0,12,42,1,7};
        sort(array, 0, array.length);
        CollectionUtil.print(array);
    }

    private void recursive(Integer[] array, Integer left, Integer right) {
        if(left >= right){
            return;
        }
        Integer base = sort(array, left, right);
        recursive(array, base+1, right);
        recursive(array, left, base-1);
    }

    private Integer sort(Integer[] array, Integer left, Integer right){
        Integer base = array[left];//基准
        Integer j = left;
        for(int i=left+1; i<=right; i++){
            if(array[i] < base){
                CollectionUtil.swap(array, i, ++j);
            }
        }
        CollectionUtil.swap(array, left, j);
        return j;
    }
}
