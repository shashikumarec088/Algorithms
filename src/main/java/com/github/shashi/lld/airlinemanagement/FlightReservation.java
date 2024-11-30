package com.github.shashi.lld.airlinemanagement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FlightReservation {
    private String reservationNumber;
    private FlightInstance flight;
    private HashMap<Passenger, FlightSeat> seatMap;
    private ReservationStatus status;
    private Date creationDate;

    public static FlightReservation fetchReservationDetails(String reservationNumber) {
        return null;
    }

    public List<Passenger> getPassengers() {
        return null;
    }
}
