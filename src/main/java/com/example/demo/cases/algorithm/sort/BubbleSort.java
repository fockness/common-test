package com.example.demo.cases.algorithm.sort;

import java.util.Arrays;
/**
 * ð��������ǰ�С��Ԫ����ǰ�����߰Ѵ��Ԫ����������Ƚ������ڵ�����Ԫ�رȽϣ�����Ҳ������������Ԫ��֮��
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
		boolean sorted = true;//�ٶ�����
		for(int i=0; i<length; i++){
			sorted = true;
			System.out.println("��" + (i+1) +"��");
			for(int j=0; j<length - i - 1; j++){
				System.out.println("��" + (j+1) +"��");
				if(array[j] > array[j+1]){
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;//�ٶ�ʧ��
				}
			}
			if(sorted){//ĳһ��û�л�λ,��Ĭ����������˳�ѭ��
				break;
			}
		}
	}

	private static void bubbleSort2(int[] array) {
		for(int i=0; i<array.length-i; i++) {
			for(int j=0; j<array.length-i; j++) {
//				if() {
//					
//				}
			}
		}
	}
}


