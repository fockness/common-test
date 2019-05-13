package com.example.demo.cases.designpattern.dynamicproxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Person {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonDao personDao = new PersonDaoImpl();
		InvocationHandler handler = new PersonHandler(personDao);
		PersonDao dao = (PersonDao)Proxy.newProxyInstance(personDao.getClass()
				.getClassLoader(), personDao.getClass().getInterfaces(), handler);
		dao.add();
		
	}

}
