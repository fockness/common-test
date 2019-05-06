package com.example.demo.cases.algorithm.search;

/**
 * 
 *折半查找法(二分查找)
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {23,34,45,56,67,87,90,111,1234};  
		System.out.println(biSearch(a, 23));
	}
	
	public static Integer biSearch(int[] a, int key){
		int middle = a.length / 2;
		if(a[middle] == key) return middle;
		int left = 0;
		int right = a.length-1;
		while(left <= right){
			middle = (left + right) / 2;
			if(key < a[middle]){
				right = middle -1;
			}else if(key > a[middle]){
				left = middle + 1;
			}else if(key == a[middle]){
				return middle+1;
			}
		}
		return null;
	}
}
