package com.example.demo.cases.designpattern.bridge;

abstract class Computer {
	
	protected Brand brand;
	
	public Computer(Brand brand) {
		this.brand = brand;
	}
	
	abstract void sale();
	
}
class Desktop extends Computer{

	public Desktop(Brand brand) {
		super(brand);
	}

	@Override
	void sale() {
		System.out.println("销售台式机");
		brand.sale();
	}
}

class Laptop extends Computer{

	public Laptop(Brand brand) {
		super(brand);
	}

	@Override
	void sale() {
		System.out.println("销售笔记本");
		brand.sale();
	}

}

