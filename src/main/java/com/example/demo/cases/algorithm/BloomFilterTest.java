package com.example.demo.cases.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author shibin
 * @version ����ʱ�䣺2019��3��1�� ����10:48:25
 * �ο�https://blog.csdn.net/tianyaleixiaowu/article/details/74739827
 */
public class BloomFilterTest {

	private static int size = 1000000;

	private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

	public static void main(String[] args) {
		for (int i = 0; i < size; i++) {
			bloomFilter.put(i);
		}

		for (int i = 0; i < size; i++) {
			if (!bloomFilter.mightContain(i)) {
				System.out.println("�л���������");
			}
		}

		List<Integer> list = new ArrayList<Integer>(1000);
		for (int i = size + 10000; i < size + 20000; i++) {
			if (bloomFilter.mightContain(i)) {
				list.add(i);
			}
		}
		System.out.println("�����˵�������" + list.size());
	}
}
