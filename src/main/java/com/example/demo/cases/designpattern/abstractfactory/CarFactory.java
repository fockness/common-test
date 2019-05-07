package com.example.demo.cases.designpattern.abstractfactory;

public interface CarFactory {

	Tyre createTyre();
	Seat createSeat();
	Engine createEngine();
	
}

class LowCarFactory implements CarFactory{

	@Override
	public Tyre createTyre() {
		return new LowTyre();
	}

	@Override
	public Seat createSeat() {
		return new LowSeat();
	}

	@Override
	public Engine createEngine() {
		return new LowEngine();
	}
	
}

class LuxuryCarFactory implements CarFactory{

	@Override
	public Tyre createTyre() {
		return new LuxuryTyre();
	}

	@Override
	public Seat createSeat() {
		return new LuxurySeat();
	}

	@Override
	public Engine createEngine() {
		return new LuxuryEngine();
	}
	
}