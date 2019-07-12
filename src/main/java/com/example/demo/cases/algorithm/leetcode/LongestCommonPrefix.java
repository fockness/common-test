package com.example.demo.cases.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author shibin
 * @version ����ʱ�䣺2019��4��16�� ����7:42:58 
 * �ַ�������������ǰ׺
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] strings = new String[10];
		List<Integer> list = Lists.newArrayList(1,2,3,4);
		System.out.println(list.remove(0));
		System.out.println(list);
	}

	/**
	 * ˮƽɨ�跨
	 * @param strs
	 * @return
	 */
	private String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}
}
