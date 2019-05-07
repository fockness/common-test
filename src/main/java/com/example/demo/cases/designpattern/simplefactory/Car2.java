package com.example.demo.cases.designpattern.simplefactory;

public interface Car2 {
	void run();
}

class Audi2 implements Car2{
	
	private static Audi2 audi2 = new Audi2();
	
	private Audi2(){}
	
	public static Audi2 newInstance(){
		return audi2;
	}

	@Override
	public void run() {
		System.out.println("Audi2.run()");
	}
}

class Byd2 implements Car2{
	
	private static Byd2 byd2;
	
	private Byd2(){}
	
	public static synchronized Byd2 newInstance(){
		if(byd2 == null){
			byd2 = new Byd2();
		}
		return byd2;
	}
	
	@Override
	public void run() {
		System.out.println("Byd2.run()");
	}
}

class CarFactory2{
	
	public static Car2 getCar(String s){
		if("Audi".equals(s)){
			return Audi2.newInstance();
		}else{
			return Byd2.newInstance();
		}
	}
	
}

