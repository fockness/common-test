package com.example.demo.cases.designpattern.dynamicproxy_jdk.test;


import com.example.demo.cases.designpattern.dynamicproxy_jdk.InvocationHandler;
import com.example.demo.cases.designpattern.dynamicproxy_jdk.Proxy;

public class Client {
	public static void main(String[] args) throws Exception {
		UserMgr mgr = new UserMgrImpl();
		InvocationHandler h = new TransactionHandler(mgr);
		//TimeHandler h2 = new TimeHandler(h);
		UserMgr u = (UserMgr) Proxy.newProxyInstance(UserMgr.class,h);
		u.addUser();
	}
}
