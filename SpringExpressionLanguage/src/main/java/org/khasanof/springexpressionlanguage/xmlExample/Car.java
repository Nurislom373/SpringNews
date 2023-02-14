package org.khasanof.springexpressionlanguage.xmlExample;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 8:50 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.xml
 */
public class Car {
    private String make;
    private int model;
    private Engine engine;
    private int horsePower;

    public Car() {
    }

    public Car(String make, int model, Engine engine, int horsePower) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.horsePower = horsePower;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model=" + model +
                ", engine=" + engine +
                ", horsePower=" + horsePower +
                '}';
    }
}
