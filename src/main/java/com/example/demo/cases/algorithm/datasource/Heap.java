package com.example.demo.cases.algorithm.datasource;

import com.example.demo.cases.algorithm.utill.CollectionUtil;

/**
 * 堆
 */
public class Heap {

    private Integer[] array;
    private Integer count;

    /**
     * 创建泛型数组 array = (T[])new Integer[capacity]
     * @param capacity
     */
    public Heap(Integer capacity){
        array = new Integer[capacity];
        count = 0;
    }

    public Heap(){
        array = new Integer[12];
        count = 0;
    }

    public Integer size(){
        return array.length;
    }

    public Boolean isEmpty(){
        return array.length == 0;
    }

    public void insert(Integer t){
        if(count+1 > array.length){
            throw new RuntimeException("越界");
        }
        array[count] = t;
        //对count位置的元素进行调整
        shiftUp(count);
        count++;
    }

    /**
     * 往既有的最大堆中添加新的元素
     */
    private void shiftUp(Integer index){
        boolean isLeft = true;
        //判断是否为右子树
        if(index/2 == 0){
            isLeft = false;
        }
        while(index > 1){
            if(isLeft && array[index] > array[(index-1)/2]){
                CollectionUtil.swap(array, index, (index-1)/2);
                index = (index-1)/2;
            }
            if(!isLeft && array[index] > array[(index-2)/2]){
                CollectionUtil.swap(array, index, (index-2)/2);
                index = (index-2)/2;
            }
        }
    }

    public Integer remove(){
        Integer max = array[0];
        array[0] = array[count--];
        shiftDown(1);
        return max;
    }

    /**
     * 将第一个元素向下移
     * @param index
     */
    private void shiftDown(Integer index){
        while(index*2 <= count){
            Integer j = index*2;//左节点,在此轮循环中array[index]与array[j]交换位置
            if(j+1 <= count && array[j] < array[j+1]){
                j+=1;
            }
            if(array[index] > array[j]){
                break;
            }
            CollectionUtil.swap(array, index, j);
            index = j;//在下一轮循环中继续查找
        }
    }

    public Integer get(Integer index){
        if(index > array.length){
            throw new RuntimeException("越界");
        }
        return array[index];
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(2);
        heap.insert(1);
        heap.insert(6);
        heap.insert(3);
        for(Integer i = 0; i<heap.count; i++){
            System.out.println(heap.array[i]);
        }
    }
}
