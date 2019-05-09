package com.example.demo.cases.designpattern.builder;

public class AirShip {

	private OrbitalModule orbitalModule;
	private EscapeTower escapeTower;
	private Engine engine;
	
	public OrbitalModule getOrbitalModule() {
		return orbitalModule;
	}
	public void setOrbitalModule(OrbitalModule orbitalModule) {
		this.orbitalModule = orbitalModule;
	}
	public EscapeTower getEscapeTower() {
		return escapeTower;
	}
	public void setEscapeTower(EscapeTower escapeTower) {
		this.escapeTower = escapeTower;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}

class OrbitalModule{
	
	public OrbitalModule(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrbitalModule{" +
				"name='" + name + '\'' +
				'}';
	}
}

class EscapeTower{
	public EscapeTower(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EscapeTower{" +
				"name='" + name + '\'' +
				'}';
	}
}

class Engine{
	
	public Engine(String name) {
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Engine{" +
				"name='" + name + '\'' +
				'}';
	}
}

