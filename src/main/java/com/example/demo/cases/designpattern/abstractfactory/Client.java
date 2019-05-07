package com.example.demo.cases.designpattern.abstractfactory;

public class Client {
	public static void main(String[] args) {
		CarFactory c = new LowCarFactory();
		Tyre tyre = c.createTyre();
		tyre.run();
	}
}
