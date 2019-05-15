package com.example.demo.cases.designpattern.dynamicproxy_jdk.test;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void add() {
		System.out.println("add success!");
	}

}
