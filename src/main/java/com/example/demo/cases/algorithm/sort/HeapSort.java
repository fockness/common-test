package com.example.demo.cases.algorithm.sort;

import com.example.demo.cases.algorithm.utill.CollectionUtil;
import org.junit.Test;

/**
 * 堆排序
 */
public class HeapSort {

    @Test
    public void testHeapSort(){
        Integer[] array = {4,1,6,2,7,3};
        adjust(array);
        CollectionUtil.print(array);
    }

    private void adjust(Integer[] array){
        //从最后一个非叶子节点开始堆化
        for(Integer i=array.length/2-1; i>=0; i--){
            heapify(array, array.length, i);
        }
        //每次排序后重新堆化
        for(Integer i=array.length-1; i>=1; i--){
            Integer temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    /**
     * 堆化
     */
    private void heapify(Integer[] array, Integer length, Integer i){
        Integer k=i, temp = array[i], index = k*2+1;
        while(index < length){
            //先比较左右子树大小，若存在右子树的话
            if(index + 1 < length){
                if(array[index] < array[index+1]){
                    index += 1;
                }
            }
            if(array[index] > temp){
                array[k] = array[index];//k一直指向父节点，index指向子节点
                k = index;
                index = k*2+1;//继续向下堆化
            }else{
                break;
            }
        }
        array[k] = temp;
    }
}
