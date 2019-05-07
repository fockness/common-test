package com.example.demo.cases.designpattern.factorymethod;

public class Client {

	public static void main(String[] args) {
		Car c = new AudiFactory().getCar();
		c.run();
		
		Car c2 = new BydFactory().getCar();
		c2.run();
	}

}
