package com.github.shashi.lld.amazonlocker;

import java.util.List;

public class LockerService {
    private List<LockerLocation> locations;

    // The LockerService is a Singleton class that ensures it will have only one active instance at a time
    private static LockerService lockerService = null;

    // Created a static method to access the Singleton instance of LockerService class
    public static LockerService getInstance() {
        if (lockerService == null) {
            lockerService = new LockerService();
        }
        return lockerService;
    }
}