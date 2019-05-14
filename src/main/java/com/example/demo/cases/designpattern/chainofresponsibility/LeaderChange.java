package com.example.demo.cases.designpattern.chainofresponsibility;

import com.example.demo.cases.designpattern.chainofresponsibility.change.Chain;

//抽象类
public abstract class LeaderChange {

	public void execute(Chain chain){
		handleRequest();
		chain.proceed();
	}

	public abstract void handleRequest();

}

class LeaderA extends LeaderChange{

	@Override
	public void handleRequest() {
		System.out.println("LeaderA.handleRequest()");
	}
}

class LeaderB extends LeaderChange{

	@Override
	public void handleRequest() {
		System.out.println("LeaderB.handleRequest()");
	}
}

class LeaderC extends LeaderChange{

	@Override
	public void handleRequest() {
		System.out.println("LeaderC.handleRequest()");
	}
}
