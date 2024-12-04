package com.github.shashi.lld.movieticketbooking;

import java.util.List;

public class Customer extends Person {
    private List<Booking> bookings; // List of bookings

    // booking here refers to an instance of the Booking class
    public boolean createBooking(Booking booking) {
        return false;
    }

    public boolean updateBooking(Booking booking) {
        return false;
    }

    public boolean deleteBooking(Booking booking) {
        return false;
    }
}
