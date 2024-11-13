package com.github.shashi.lld.vendingmachine;

public class MoneyInsertedState implements State {
    @Override
    public void insertMoney(VendingMachine machine, double amount) {}
    public void pressButton(VendingMachine machine, int rackNumber) {
        // check if product item is available
        // validate money
        // change state to DispenseState
    }
    public void returnChange(double amount) {}
    public void updateInventory(VendingMachine machine, int rackNumber) {}
    public void dispenseProduct(VendingMachine machine, int rackNumber) {}
}