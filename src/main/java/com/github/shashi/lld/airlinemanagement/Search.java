package com.github.shashi.lld.airlinemanagement;

import java.util.Date;
import java.util.List;

public interface Search {
    // Interface method (does not have a body)
    public List<FlightInstance> searchFlight(Airport source, Airport dest, Date arrival, Date departure);
}
