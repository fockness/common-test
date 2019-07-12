package com.example.demo.cases.algorithm.leetcode;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * leetcode283
 * 双指针法
 */
public class MoveZeros {

    @Test
    public void testMoveZeros(){
        Integer[] array = {3,1,6,0,56,29,0,0,8,2};
        move(array);
        CollectionUtil.print(array);
    }

    private void move(Integer[] array){
        int firstPir = 0;
        for(int i=0; i<array.length; i++){
            if(array[i] != 0){
                if(firstPir != i){
                    array[firstPir++] = array[i];
                    array[i] = 0;
                }else{
                    firstPir++;
                }
            }
        }
    }
}
