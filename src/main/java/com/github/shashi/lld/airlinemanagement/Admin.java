package com.github.shashi.lld.airlinemanagement;

public class Admin extends Person {
    public boolean addAircraft(Aircraft aircraft) {
        return false;
    }

    public boolean addFlight(Flight flight) {
        return false;
    }

    public boolean cancelFlight(Flight flight) {
        return false;
    }

    public boolean assignCrew(Flight flight) {
        return false;
    }

    public boolean blockUser(Customer user) {
        return false;
    }

    public boolean unblockUser(Customer user) {
        return false;
    }
}
