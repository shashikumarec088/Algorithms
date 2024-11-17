package com.github.shashi.lld.carrentalsystem;

import java.util.List;

public abstract class Vehicle {
    private String vehicleId;
    private String licenseNumber;
    private int passengerCapacity;
    private boolean hasSunroof;
    private VehicleStatus status;
    private String model;
    private int manufacturingYear;
    private int mileage;
    private List<VehicleLog> log;

    public abstract boolean reserveVehicle();
    public abstract boolean returnVehicle();
}