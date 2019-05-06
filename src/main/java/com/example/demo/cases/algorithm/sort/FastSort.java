package com.example.demo.cases.algorithm.sort;

import java.util.Arrays;

public class FastSort {
	
	public static void main(String[] args) {
		int[] a = {35, 182, 94, 112, 4843, 23, 123, 48, 94, 35};
		fastSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}

	public static void fastSort(int a[], int low, int high){
		 int start = low;
         int end = high;
         int key = a[low];
         
         while(end>start){
             //�Ӻ���ǰ�Ƚ�
             while(end>start&&a[end]>=key)  //���û�бȹؼ�ֵС�ģ��Ƚ���һ����ֱ���бȹؼ�ֵС�Ľ���λ�ã�Ȼ���ִ�ǰ����Ƚ�
                 end--;
             if(a[end]<=key){
                 int temp = a[end];
                 a[end] = a[start];
                 a[start] = temp;
             }
             //��ǰ����Ƚ�
             while(end>start&&a[start]<=key)//���û�бȹؼ�ֵ��ģ��Ƚ���һ����ֱ���бȹؼ�ֵ��Ľ���λ��
                start++;
             if(a[start]>=key){
                 int temp = a[start];
                 a[start] = a[end];
                 a[end] = temp;
             }
         //��ʱ��һ��ѭ���ȽϽ������ؼ�ֵ��λ���Ѿ�ȷ���ˡ���ߵ�ֵ���ȹؼ�ֵС���ұߵ�ֵ���ȹؼ�ֵ�󣬵������ߵ�˳���п����ǲ�һ���ģ���������ĵݹ����
         }
         //�ݹ�
         if(start>low) fastSort(a,low,start-1);//������С���һ������λ�õ��ؼ�ֵ����-1
         if(end<high) fastSort(a,end+1,high);//�ұ����С��ӹؼ�ֵ����+1�����һ��
	}
}
