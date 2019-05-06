package com.test.algorithm.sort;
/**
* @author shibin
* @version ����ʱ�䣺2019��4��18�� ����6:49:32
* ֱ�Ӳ�������
*/
public class DirectInsertionSort {

	public static void main(String[] args) {
		Integer[] nums = {10, 12, 18, 1, 7, 99, 18, 15};
		directInsertionSort(nums);
		for(Integer integer : nums) {
			System.out.println(integer);
		}
	}
	
	private static void directInsertionSort(Integer[] nums) {
		for(int i=1; i<nums.length; i++) {
			for(int j=i; j>0;  j--) {
				if(nums[j] < nums[j-1]) {
					int temp = nums[j];
					nums[j] = nums[j-1];
					nums[j-1] = temp;
				}
			}
		}
	}
}
