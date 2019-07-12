package com.example.demo.cases.algorithm.leetcode;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * leetcode88
 *双指针法
 */
public class MergeSortedArray {

    @Test
    public void testMergeSortedArray(){
        Integer[] a = {1,4,6,2,43,8,2};//1,2,2,4,6,8,43
        Integer[] b = {4,2,1,5,7,3};//1,2,3,4,5,7
        CollectionUtil.print(sort(a, b));
    }

    private Integer[] sort(Integer[] a, Integer[] b){
        bubbleSort(a);
        bubbleSort(b);
        Integer[] c = new Integer[a.length+b.length];
        int index = 0;
        int i = 0;
        int j = 0;
        for(;;){
            if(a[i] > b[j]){
                c[index++] = b[j++];
            }else if(a[i] <= b[j]){
                c[index++] = a[i++];
            }
            if(i==a.length){
                break;
            }
            if(j==b.length){
                break;
            }
        }
        if(i<a.length){
            System.arraycopy(a, i, c, index, a.length-i);
        }
        if(j<b.length){
            System.arraycopy(b, j, c, index, b.length-j);
        }
        return c;
    }

    private void bubbleSort(Integer[] array){
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array.length-i-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
