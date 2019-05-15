package com.example.demo.cases.designpattern.mediator;

public class Client {
	public static void main(String[] args) {
		Mediator m = new President();
		Department market = new Market(m);
		Department deve = new Development(m);
		Department fin = new Financial(m);
		
		market.selfAction();
		market.outAction();
	}
}
