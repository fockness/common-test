package com.example.demo.cases.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {12,5,7,8,2,4,6,17,423};
        int gap = 0;
        for(gap = array.length/2; gap > 0; gap /= 2){
            for(int i = gap; i < array.length; i++){
                for(int j = i; j - gap >= 0; j -= gap){
                    swap(array, j-gap, j);
                }
            }
        }
        for(int i=0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }

    private static void swap(int [] array, int i, int j){
        if(array[i] > array[j]){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}


