package com.github.shashi.lld.carrentalsystem;

import java.util.Date;
import java.util.List;

public class VehicleReservation {
    private int reservationId;
    private String customerId;
    private String vehicleId;
    private Date creationDate;
    private ReservationStatus status;
    private Date dueDate;
    private Date returnDate;
    private String pickupLocation;
    private String returnLocation;

    private List<Equipment> equipments;
    private List<Service> services;

    public VehicleReservation getReservationDetails() {
        return null;
    }

    public boolean addEquipment() {
        return false;
    }

    public boolean addService() {
        return false;
    }
}