package com.example.demo.cases.designpattern.adapter;

public class Client {
	
//	public void test(){
//		
//	}这个方法想调用Adaptee的request()但是接口不匹配，可以把适配器当作参数传入

	public void test(Target t){
		t.handleReq();
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		
		Adaptee adaptee = new Adaptee();
		
		Target target = new Adapter(adaptee);
		
		client.test(target);
		
		
	}
}
