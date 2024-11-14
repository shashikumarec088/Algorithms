package com.github.shashi.lld.movieticketbooking;

public class Customer extends Person {
    private List<Bookings> bookings; // List of bookings

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
