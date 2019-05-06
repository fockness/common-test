package com.example.demo.cases.algorithm;
/**
* @author shibin E-mail : shibin@uama.com.cn
* @Date creation time 锛�2018-09-04 11:25
* @Description
*	
*/
public class ReverseInteger {
	
	public static void main(String[] args) {
		System.out.println(reverse(123));
	}

	private static int reverse(int x) {
		long out = 0;
		while(x != 0) {
			out = out * 10 + x % 10;
			x = x / 10;
		}
		if(out > Integer.MAX_VALUE || out < Integer.MIN_VALUE) return 0;
		return (int)out;
	}
}
