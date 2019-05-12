package com.example.demo.cases.designpattern.decorator;

/*
 *	装饰器对象和原来对象实现同一接口,这样,装饰器对象与原来对象拥有相同方法,装饰器对象构造方法中有原来对象的引用,
 *  想使用新方法就写装饰器对象里,不想改的就调用原来对象的方法
 *
 */

//抽象构建
public interface ICar {
	void move();
}

//具体构建（具体角色）
class Car implements ICar{
	@Override
	public void move() {
		System.out.println("陆上跑");
	}
}

//装饰器构建
class SuperCar implements ICar{

	protected ICar car;

	public SuperCar(ICar car) {
		this.car = car;
	}

	@Override
	public void move() {
		car.move();
	}
}

//装饰器具体构建
class FlyCar extends SuperCar{

	public FlyCar(ICar car) {
		super(car);
	}

	@Override
	public void move() {
		super.move();
		fly();
	}

	public void fly(){
		System.out.println("天上飞");
	}
}

//装饰器具体构建
class WaterCar extends SuperCar{

	public WaterCar(ICar car) {
		super(car);
	}

	@Override
	public void move() {
		super.move();
		swim();
	}

	public void swim(){
		System.out.println("水里游");
	}
}

//装饰器具体构建
class AICar extends SuperCar{

	public AICar(ICar car) {
		super(car);
	}

	@Override
	public void move() {
		super.move();
		autoMove();
	}

	public void autoMove(){
		System.out.println("自动跑");
	}
}