package com.example.demo.cases.designpattern.simplefactory;

public interface Car {
	void run();
}

class Byd implements Car {

	@Override
	public void run() {
		System.out.println("Byd run");
	}
}

class Audi implements Car {

	@Override
	public void run() {
		System.out.println("Audi run");
	}
}

