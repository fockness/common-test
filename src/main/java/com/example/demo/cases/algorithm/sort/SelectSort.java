package com.example.demo.cases.algorithm.sort;

import java.util.Arrays;

public class SelectSort {
	
	public static void main(String[] args) {
		int[] a = {35, 182, 94, 112, 4843, 23, 123, 48, 94, 35};
		selectSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void selectSort(int[] a){
		for(int i=0; i<a.length-1; i++){
			int min = i;
			for(int j=min+1; j<a.length; j++){
				if(a[j] < a[min]){
					min = j;
				}
			}
			if(i != min){
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
	}
}
