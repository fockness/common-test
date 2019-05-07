package com.example.demo.cases.designpattern.factorymethod;

public interface Car {
	void run();
}

class Audi implements Car {

	@Override
	public void run() {
		System.out.println("Audi run");
	}
}

class Byd implements Car {

	@Override
	public void run() {
		System.out.println("Byd run");
	}
}
