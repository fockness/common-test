package com.example.demo.cases.designpattern.abstractfactory;

public interface Tyre {
	
	void run();
	
}

class LowTyre implements Tyre{

	@Override
	public void run() {
		System.out.println("LowTyre");
	}
	
}

class LuxuryTyre implements Tyre{

	@Override
	public void run() {
		System.out.println("LuxuryTyre");
	}
	
}