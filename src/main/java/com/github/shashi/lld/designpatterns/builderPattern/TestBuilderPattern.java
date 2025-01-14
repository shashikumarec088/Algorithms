package com.github.shashi.lld.designpatterns.builderPattern;

public class TestBuilderPattern {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder()
                .setMake("Toyota")
                .setModel("Camry")
                .setYear(2021)
                .setColor("Blue")
                .setSunroof(true)
                .build();
        System.out.println("Car built: " + car);
    }
}
class Car {
    private String make;
    private String model;
    private int year;
    private String color;
    private boolean sunroof;

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", sunroof=" + sunroof +
                '}';
    }

    private Car(CarBuilder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.color = builder.color;
        this.sunroof = builder.sunroof;
    }

    public static class CarBuilder {
        private String make;
        private String model;
        private int year;
        private String color;
        private boolean sunroof;

        public CarBuilder setMake(String make) { this.make = make; return this; }
        public CarBuilder setModel(String model) { this.model = model; return this; }
        public CarBuilder setYear(int year) { this.year = year; return this; }
        public CarBuilder setColor(String color) { this.color = color; return this; }
        public CarBuilder setSunroof(boolean sunroof) { this.sunroof = sunroof; return this; }

        public Car build() { return new Car(this); }
    }
}