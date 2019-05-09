package com.example.demo.cases.designpattern.builder;


//这个类用于创建实体类的组件
public interface AirShipBuilder {
	OrbitalModule buildOrbitalModule();
	EscapeTower buildEscapeTower();
	Engine buildEngine();
}

class SxtAirShipBuilder implements AirShipBuilder{

	@Override
	public OrbitalModule buildOrbitalModule() {
		System.out.println("创建轨道舱");
		return new OrbitalModule("轨道舱创建成功");//这里的创建对象根据情况可以选择单例模式或者工厂模式
	}

	@Override
	public EscapeTower buildEscapeTower() {
		System.out.println("创建逃逸塔");
		return new EscapeTower("逃逸塔创建成功");
	}

	@Override
	public Engine buildEngine() {
		System.out.println("创建发动机");
		return new Engine("发动机创建成功");
	}
	
}
