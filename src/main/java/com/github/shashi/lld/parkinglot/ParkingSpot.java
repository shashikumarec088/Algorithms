package com.github.shashi.lld.parkinglot;

public abstract class ParkingSpot {
    private int id;
    private boolean isFree;
    private Vehicle vehicle;

    public boolean getIsFree() {
        return false;
    }

    public abstract boolean assignVehicle(Vehicle vehicle);
    public boolean removeVehicle(){
        // definition
        return true;
    }
}
