package com.github.shashi.lld.parkinglot;

public abstract class Vehicle {
    private int licenseNo;
    public abstract void assignTicket(ParkingTicket ticket);
}