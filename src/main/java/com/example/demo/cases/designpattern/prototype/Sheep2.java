package com.example.demo.cases.designpattern.prototype;

import java.util.Date;

//深克隆
public class Sheep2 implements Cloneable{
	
	private String name;
	private Date birthday;
	public Sheep2(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object object =  super.clone();
		Sheep2 sheep2 = (Sheep2)object;
		sheep2.birthday = (Date)this.birthday.clone();
		return object;
	}
}
