package com.example.demo.cases.designpattern.dynamicproxy_jdk.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonHandler implements InvocationHandler {
	
	private Object target;
	
	public PersonHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("start!");
		Object result = method.invoke(target, null);
		System.out.println("fail!");
		return result;
	}

}
