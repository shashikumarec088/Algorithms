package com.github.shashi.lld.airlinemanagement;

import java.util.List;

public class Airline {
    private String name;
    private String code;
    private List<Flight> flights;
    private List<Aircraft> aircrafts;
    private List<Crew> crew;

    // The Airline is a singleton class that ensures it will have only one active instance at a time
    private static Airline airline = null;

    // Created a static method to access the singleton instance of Airline class
    public static Airline getInstance() {
        if (airline == null) {
            airline = new Airline();
        }
        return airline;
    }
}