package com.example.demo.cases.algorithm.sort;

import java.util.Arrays;

/**
 * 
 *插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据。
 *
 */
public class
InsertSort {
	
	public static void main(String[] args) {
		int[] a = {35, 182, 94, 112, 4843, 23, 123, 48, 94, 35};
		insertSort(a);
		System.out.println(Arrays.toString(a));
	}

	//基本
	public static void insertSort(int[] a){
		int length = a.length;
		for(int i=0; i<length; i++){
			for(int j=i; j>0; j--){
				if(a[j-1] > a[j]){
					int temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	//优化
	public static void insertSort2(int[] a){
		int length = a.length;
		for(int i=0; i<length; i++){
			int e = a[i];
			int j;
			for(j=i; j>0&&a[j-1]>e; j--){
				a[j] = a[j-1];
			}
			a[j] = e;
		}
	}
	
}
