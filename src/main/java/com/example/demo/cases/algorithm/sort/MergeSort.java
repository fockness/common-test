package com.example.demo.cases.algorithm.sort;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-20 15:34
 * 4    归并排序是一种稳定的排序。O(nlgn)
 */
public class MergeSort {

    @Test
    public void testMergeSort(){
        Integer[] array = {2,3,5,1,7,3,5,23,12,75,22,1,0};
        sort(array, 0, array.length-1);
        CollectionUtil.print(array);
    }

    private Integer[] sort(Integer[] array, Integer low, Integer high){
        if(low < high){
            Integer mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid+1, high);
            merge(array, low, high, mid);
        }
        return array;
    }

    private void merge(Integer[] array, Integer low, Integer high, Integer mid){
        Integer[] temp = new Integer[high-low+1];//需要一个中间数组
        Integer i = low;
        Integer j = mid+1;
        Integer k=0;
        //把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(array[i] < array[j]){
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = array[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = array[j++];
        }
        // 把新数组中的数覆盖原数组
        for(int t=0; t<k; t++){
            array[low+t] = temp[t];
        }
    }

}
