package com.example.demo.cases.designpattern.builder;

/**
 * 建造者模式同样也适用于大量参数在方法里传递，建造者模式可以使用jdk8里的consumer实现
 */
public class Client {

	public static void main(String[] args) {
		AirShipDirector director = new SxtAirShipDirector(new SxtAirShipBuilder());
		AirShip ship = director.createAirShip();
		System.out.println(ship.getEngine().getName());
	}
}
