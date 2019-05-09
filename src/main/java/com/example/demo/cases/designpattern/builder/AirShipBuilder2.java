package com.example.demo.cases.designpattern.builder;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-9 10:57
 * 4建造者类内部引用真实对象，并动态为他的属性赋值
 */
public class AirShipBuilder2 {

    private AirShip airShip;

    public AirShipBuilder2(){
        airShip = new AirShip();
    }

    public AirShipBuilder2 buildOrbitalModule(String name){
        airShip.setOrbitalModule(new OrbitalModule(name));
        return this;
    }

    public AirShipBuilder2 buildEscapeTower(String name){
        airShip.setEscapeTower(new EscapeTower(name));
        return this;
    }

    public AirShipBuilder2 buildEngine(String name){
        airShip.setEngine(new Engine(name));
        return this;
    }

    public AirShip build(){
        return airShip;
    }

    public static void main(String[] args) {
        AirShip ship = new AirShipBuilder2().buildOrbitalModule("轨道舱1").buildEscapeTower("逃逸塔1").buildEngine("引擎1").build();
        AirShip ship2 = new AirShipBuilder2().buildOrbitalModule("轨道舱2").buildEscapeTower("逃逸塔2").buildEngine("引擎2").build();
        System.out.println(ship.getEngine()+"-"+ship.getEscapeTower()+"-"+ship.getOrbitalModule());
        System.out.println(ship2.getEngine()+"-"+ship2.getEscapeTower()+"-"+ship2.getOrbitalModule());

    }
}
