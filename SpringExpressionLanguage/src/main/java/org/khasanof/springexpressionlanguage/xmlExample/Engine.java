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
public class Engine {

    private int capacity;
    private int horsePower;
    private int numberOfCylinders;

    public Engine() {
    }

    public Engine(int capacity, int horsePower, int numberOfCylinders) {
        this.capacity = capacity;
        this.horsePower = horsePower;
        this.numberOfCylinders = numberOfCylinders;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "capacity=" + capacity +
                ", horsePower=" + horsePower +
                ", numberOfCylinders=" + numberOfCylinders +
                '}';
    }
}
