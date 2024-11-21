package com.github.shashi.lld.carrentalsystem;

import java.util.List;

public class CarRentalSystem {
    private String name;
    private List<CarRentalBranch> branches;

    public void addNewBranch(CarRentalBranch branch) {

    }

    // The CarRentalSystem is a singleton class that ensures it will have only one active instance at a time
    private static CarRentalSystem system = null;

    // Created a static method to access the singleton instance of CarRentalSystem class
    public static CarRentalSystem getInstance() {
        if (system == null) {
            system = new CarRentalSystem();
        }
        return system;
    }
}