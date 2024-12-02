package com.github.shashi.lld.airlinemanagement;

import java.util.Date;
import java.util.List;

public class FlightInstance {
    private Flight flight;
    private Date departureTime;
    private String gate;
    private FlightStatus status;
    private Aircraft aircraft;
    private List<FlightSeat> seats;
}