package com.example.demo.cases.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibin
 * @version 创建时间：2019年3月1日 上午10:48:25
 * 参考https://blog.csdn.net/tianyaleixiaowu/article/details/74739827
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
				System.out.println("有坏人逃脱了");
			}
		}

		List<Integer> list = new ArrayList<Integer>(1000);
		for (int i = size + 10000; i < size + 20000; i++) {
			if (bloomFilter.mightContain(i)) {
				list.add(i);
			}
		}
		System.out.println("有误伤的数量：" + list.size());
	}
}
