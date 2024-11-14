package com.github.shashi.lld.movieticketbooking;

public abstract class Seat {
    // Data members
    private String seatNo;
    private SeatStatus status; // Refers to the SeatStatus enum

    // Member functions
    public boolean isAvailable() {
        return false;
    }

    public abstract void setSeat();
    public abstract void setRate();
}