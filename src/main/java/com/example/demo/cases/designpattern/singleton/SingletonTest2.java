package com.example.demo.cases.designpattern.singleton;

public class SingletonTest2 {

	public static void main(String[] args) {
		Single single = Single.INSTANCE;
		single.operate();
		
		StaticSingle.newInstance().say();
	}
}

enum Single{
	
	INSTANCE;
	
	public static void operate(){
		System.out.println("1");
	}
}


class StaticSingle{
	
	private static class Single{
		private static StaticSingle single = new StaticSingle();
	}
	
	private StaticSingle(){}
	
	public static StaticSingle newInstance(){
		return Single.single;
	}
	
	public void say(){
		System.out.println("say");
	}
}
