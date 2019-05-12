package com.example.demo.cases.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class MainFolder {
	protected String name;
	
	public void add(MainFolder e){};
	
	protected abstract void killVirus();
}

class Image extends MainFolder{
	
	public Image(String name){
		this.name = name;
	}

	@Override
	protected void killVirus() {
		System.out.println("正在查杀" + name + ".jpg");
	}
}

class Video extends MainFolder{
	
	public Video(String name){
		this.name = name;
	}
	
	@Override
	protected void killVirus() {
		System.out.println("正在查杀" + name + ".avi");
	}
}

class SubFolder extends MainFolder{
	
	private List<MainFolder> list = new ArrayList<MainFolder>();
	
	@Override
	public void add(MainFolder e) {
		list.add(e);
	}

	@Override
	protected void killVirus() {
		for(MainFolder e : list){
			e.killVirus();
		}
	}
}