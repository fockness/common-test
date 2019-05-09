package com.example.demo.cases.designpattern.builder;

//这个类用于组装实体类的组件并返回组装后的实体类
public interface AirShipDirector {
	AirShip createAirShip();
}

class SxtAirShipDirector implements AirShipDirector{

	private AirShipBuilder builder;

	public SxtAirShipDirector(AirShipBuilder builder) {
		super();
		this.builder = builder;
	}

	@Override
	public AirShip createAirShip() {
		Engine engine = builder.buildEngine();
		EscapeTower escapeTower = builder.buildEscapeTower();
		OrbitalModule orbitalModule = builder.buildOrbitalModule();

		AirShip ship = new AirShip();
		ship.setEngine(engine);
		ship.setEscapeTower(escapeTower);
		ship.setOrbitalModule(orbitalModule);

		return ship;
	}

}