package com.github.shashi.lld.elevator;

public class ElevatorSystem {
    private Building building;

    public void monitoring() {

    }

    public void dispatcher() {

    }

    // Private constructor to prevent direct instantiation
    private ElevatorSystem() {
        // Initialize the ElevatorSystem
    }

    // The ElevarSystem is a singleton class that ensures it will have only one active instance at a time
    private static ElevatorSystem system = null;

    // Created a static method to access the singleton instance of ElevatorSytem class
    public static ElevatorSystem getInstance() {
        if (system == null) {
            system = new ElevatorSystem();
        }
        return system;
    }
}