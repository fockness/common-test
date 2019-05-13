package com.example.demo.cases.designpattern.dynamicproxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
	public void invoke(Object o, Method m);
}
