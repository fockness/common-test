package com.example.demo.cases.designpattern.abstractfactory;

public interface Engine {
	
	void go();
}

class LowEngine implements Engine{

	@Override
	public void go() {
		System.out.println("LowEngine");
	}
}

class LuxuryEngine implements Engine{

	@Override
	public void go() {
		System.out.println("LuxuryEngine");
	}
}