package com.example.demo.cases.designpattern.chainofresponsibility.change;

import com.example.demo.cases.designpattern.chainofresponsibility.LeaderChange;

import java.util.List;

public class Chain {
	
	private List<LeaderChange> leaders;
	
	private int index = 0;
	
	public Chain(List<LeaderChange> leaders) {
		this.leaders = leaders;
	}
	
	public void proceed(){
		if(index >= leaders.size()) return;
		leaders.get(index++).execute(this);
	}
}
