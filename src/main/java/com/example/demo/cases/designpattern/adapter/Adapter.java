package com.example.demo.cases.designpattern.adapter;

/**
 * 对象适配器
 */
public class Adapter implements Target {
	
	private Adaptee adaptee;

	public Adapter(Adaptee adaptee){
		this.adaptee = adaptee;
	}
	
	@Override
	public void handleReq() {
		adaptee.request();
	}

}
