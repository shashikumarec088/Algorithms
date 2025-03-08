package com.github.shashi.java.interfaces;

interface Vehicle {
    static Vehicle createCar() {
        return new Car();
    }
    void drive();
}
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car.");
    }
}
public class Main {
    public static void main(String[] args) {
        Vehicle myVehicle = Vehicle.createCar();  // Using static factory method
        myVehicle.drive();
    }
}

