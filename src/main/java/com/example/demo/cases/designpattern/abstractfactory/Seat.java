package com.example.demo.cases.designpattern.abstractfactory;

public interface Seat {
	
	void sit();
}


class LowSeat implements Seat{

	@Override
	public void sit() {
		System.out.println("LowSeat");
	}
	
}

class LuxurySeat implements Seat{

	@Override
	public void sit() {
		System.out.println("LuxurySeat");
	}
	
}