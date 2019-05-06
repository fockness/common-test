package com.example.demo.cases.algorithm.sort;

import java.util.Arrays;
/**
 * 冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] array = {87, 832, 34, 92, 44, 11, 95, 2384, 85, 24, 20, 638};
		bubbleSort(array);
		System.out.println(Arrays.toString(array));
	}

	private static void bubbleSort(int array[]){
		int length = array.length;
		boolean sorted = true;//假定有序
		for(int i=0; i<length; i++){
			sorted = true;
			System.out.println("第" + (i+1) +"躺");
			for(int j=0; j<length - i - 1; j++){
				System.out.println("第" + (j+1) +"次");
				if(array[j] > array[j+1]){
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;//假定失败
				}
			}
			if(sorted){//某一趟没有换位,则默认排序完成退出循环
				break;
			}
		}
	}

}


