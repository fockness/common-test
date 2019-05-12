package com.example.demo.cases.designpattern.bridge;

public interface Brand {
	void sale();
}

class Lenovo implements Brand{
	@Override
	public void sale() {
		System.out.println("销售联想");
	}
}

class Dell implements Brand{
	@Override
	public void sale() {
		System.out.println("销售戴尔");
	}
}