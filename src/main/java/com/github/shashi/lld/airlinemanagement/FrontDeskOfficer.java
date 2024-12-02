package com.github.shashi.lld.airlinemanagement;

import java.util.List;

public class FrontDeskOfficer extends Person {
    public List<Itinerary> viewItinerary() {
        return null;
    }

    public boolean createItinerary() {
        return false;
    }

    public boolean createReservation() {
        return false;
    }

    public boolean assignSeat() {
        return false;
    }

    public boolean makePayment() {
        return false;
    }
}
