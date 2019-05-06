package com.example.demo.cases.algorithm;

import java.util.Stack;

/**
* @author shibin
* @version 创建时间：2019年4月17日 下午4:27:49
 * * 	有效括号
*/
public class ValidParentheses {

	public static void main(String[] args) {
		System.out.println(isValid("[{}]"));
	}
	
	private static boolean isValid(String string) {
		Stack<Character> stack = new Stack<>();
		char[] chars = string.toCharArray();
		for(char c : chars) {
			if(stack.empty()) {
				stack.add(c);
			}else if(isSys(stack.peek(), c)) {
				stack.pop();
			}else {
				stack.push(c);
			}
		}
		return stack.size() == 0;
	}
	
	private static boolean isSys(char c1, char c2) {
		return (c1 == '[' && c2 == ']')
				|| (c1 == '{' && c2 == '}')
				|| (c1 == '(' && c2 == ')');
	}
}
