package com.github.shashi.lld.carrentalsystem;

import java.util.List;

public interface Search {
    public List<Vehicle> searchByType(String type);
    public List<Vehicle> searchByModel(String model);
}
