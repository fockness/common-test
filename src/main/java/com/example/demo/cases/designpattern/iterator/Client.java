package com.example.demo.cases.designpattern.iterator;

public class Client {
	public static void main(String[] args) {
		Aggregate a = new Aggregate();
		a.addObj("aa");
		a.addObj("bb");
		a.addObj("cc");
		MyIterator iterator = a.createIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.getCurrentObj());
			iterator.next();
		}
	}
}
