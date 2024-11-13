package com.github.shashi.lld.vendingmachine;

import java.util.List;

public class VendingMachine {
    private State currentState;
    private double amount;
    private int noOfRacks;
    private List<Rack> racks;
    private List<Integer> availableRacks;

    // The VendingMachine is a Singleton class that ensures it will have only one active instance at a time
    private static VendingMachine vendingMachine = null;

    // Created a private constructor to add a restriction (due to Singleton)
    private VendingMachine() {

    }

    // Created a static method to access the singleton instance of VendingMachine
    public static VendingMachine getInstance() {
        if (vendingMachine == null) {
            vendingMachine = new VendingMachine();
        }
        return vendingMachine;
    }

    public void insertMoney(double amount) {}
    public void pressButton(int rackNumber) {}
    public void returnChange(double amount) {}
    public void updateInventory(int rackNumber) {}
    public void dispenseProduct(int rackNumber) {}
    public int getProductIdAtRack(int rackNumber) {
        return rackNumber;
    }
}