package com.example.demo.cases.algorithm.utill;

public class CollectionUtil {

    public static <T> void print(T[] array){
        for(T t : array){
            System.out.print(t+",");
        }
    }

    public static <T> void swap(T[] array, Integer i1, Integer i2){
        T t = array[i1];
        array[i2] = array[i1];
        array[i1] = t;
    }
}
