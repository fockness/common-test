package com.example.demo.cases.designpattern.simplefactory;

public class CarFactory {
	
	public static Car getCar(String name){
		if("Audi".equals(name)){
			return new Audi();
		}else{
			return new Byd();
		}
	}
	
}
