package com.example.demo.cases.algorithm.utill;

public class CollectionUtil {

    public static <T> void print(T[] array){
        for(T t : array){
            System.out.print(t+",");
        }
    }
}
