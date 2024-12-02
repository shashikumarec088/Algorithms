package com.github.shashi.lld.airlinemanagement;

import java.util.Date;
import java.util.List;

public class Itinerary {
    private Airport startingAirport;
    private Airport finalAirport;
    private Date creationDate;
    private List<FlightReservation> reservations;
    private List<Passenger> passengers;

    public boolean makeReservation() {
        return false;
    }

    public boolean makePayment() {
        return false;
    }
}