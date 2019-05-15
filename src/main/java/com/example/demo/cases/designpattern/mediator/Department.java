package com.example.demo.cases.designpattern.mediator;

//同事接口
public interface Department {
	void selfAction();
	void outAction();
}

//同事对象
class Development implements Department{

	private Mediator m;

	public Development(Mediator m) {
		super();
		this.m = m;
		m.registor("development", this);
	}

	@Override
	public void selfAction() {
		System.out.println("我在开发哟");
	}

	@Override
	public void outAction() {
		System.out.println("研发部要钱");
		m.command("financial");//通过中介类与财务部对象交互
	}
}

class Market implements Department{

	private Mediator m;

	public Market(Mediator m) {
		super();
		this.m = m;
		m.registor("market", this);
	}

	@Override
	public void selfAction() {
		System.out.println("我在跑业务哟");
	}

	@Override
	public void outAction() {
		System.out.println("市场部要钱");
		m.command("financial");//通过中介类与财务部对象交互
	}
}

class Financial implements Department{

	private Mediator m;

	public Financial(Mediator m) {
		super();
		this.m = m;
		m.registor("financial", this);
	}

	@Override
	public void selfAction() {
		System.out.println("我在数钱哟");
	}

	@Override
	public void outAction() {
		System.out.println("财务部要程序");
		m.command("development");//通过中介类与财务部对象交互
	}
}