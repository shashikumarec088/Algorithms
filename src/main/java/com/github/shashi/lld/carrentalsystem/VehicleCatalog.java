package com.github.shashi.lld.carrentalsystem;

import java.util.HashMap;
import java.util.List;

public class VehicleCatalog implements Search {
    private HashMap<String, List<Vehicle>> vehicleTypes;
    private HashMap<String, List<Vehicle>> vehicleModels;

    // to return all vehicles of the given type.
    public List<Vehicle> searchByType(String type) {
        // functionality
        return null;
    }

    // to return all vehicles of the given model.
    public List<Vehicle> searchByModel(String model) {
        // functionality
        return null;
    }
}