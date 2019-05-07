package com.example.demo.cases.designpattern.simplefactory;


//�򵥹���ģʽ
public class Client {

	public static void main(String[] args) {
		Car c = CarFactory.getCar("Byd");
		c.run();
	}

}
