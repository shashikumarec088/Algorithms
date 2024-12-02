package com.github.shashi.lld.airlinemanagement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SearchCatalog implements Search {
    // this should be Quartet<Airport, Airport, Date, Date>
    private HashMap<Airport, List<FlightInstance>> flights;

    public List<FlightInstance> searchFlight(Airport source, Airport dest, Date arrival, Date departure) {
        // functionality
        return null;
    }
}
