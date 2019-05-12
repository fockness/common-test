package com.example.demo.cases.designpattern.bridge;

public class Client {
	public static void main(String[] args) {
		Computer c = new Desktop(new Dell());
		c.sale();
		
		Computer c2 = new Laptop(new Lenovo());
		c2.sale();
	}
}
