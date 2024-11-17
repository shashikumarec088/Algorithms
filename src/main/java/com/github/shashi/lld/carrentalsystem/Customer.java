package com.github.shashi.lld.carrentalsystem;

import java.util.Date;
import java.util.List;

public class Customer extends Account {
    private String licenseNumber;
    private Date licenseExpiry;

    public boolean addReservation() {
        return false;
    }

    public boolean cancelReservation() {
        return false;
    }

    public List<VehicleReservation> getReservations() {
        return null;
    }

    public boolean resetPassword() {
        // definition
        return false;
    }
}