package com.example.demo.cases.designpattern.factorymethod;

public interface CarFactory {
	Car getCar();
}

class AudiFactory implements CarFactory {
	@Override
	public Car getCar() {
		return new Audi();
	}
}

class BydFactory implements CarFactory {
	@Override
	public Car getCar() {
		return new Byd();
	}
}
