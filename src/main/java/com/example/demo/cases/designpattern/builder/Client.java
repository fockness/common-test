package com.example.demo.cases.designpattern.builder;

/**
 * 建造者模式同样也适用于大量参数在方法里传递
 */
public class Client {

	public static void main(String[] args) {
		AirShipDirector director = new SxtAirShipDirector(new SxtAirShipBuilder());
		AirShip ship = director.createAirShip();
		System.out.println(ship.getEngine().getName());
	}
}
