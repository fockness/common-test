package com.example.demo.cases.algorithm;

/**
* @author shibin E-mail : shibin@uama.com.cn
* @Date creation time 锛�2018-09-05 15:44
* @Description
*	求出某数是否是回文数
*/
public class PalindromeNumber {

	public static void main(String[] args) {
		Integer target = 1221;
		System.out.println(isPalindromeNumber(target));
	}

	private static boolean isPalindromeNumber(Integer target) {
		if(target < 0 || (target % 10 == 0 && target != 0)) 
			return false;
		Integer x = 0;
		while(target > x) {
			x = x * 10 + target % 10;
			target /= 10;
		}
		if(x == target || x / 10 == target) {
			return true;
		}
		return false;
	}

	/**
	 * 自己的想法
	 * @param x
	 * @return
	 */
	private static boolean isPalindrome(Integer x) {
		String string = x.toString();
		Integer length = string.length();
		for(int i=0; i<length; i++) {
			if(string.charAt(i) != string.charAt(length-i-1)) {
				return false;
			}
		}
		return true;
	}
	
}
