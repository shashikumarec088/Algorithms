package com.github.shashi.lld.vendingmachine;

public class NoMoneyInsertedState implements State {
    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        // changes state to MonenInsertedState
    }
    public void pressButton(VendingMachine machine, int rackNumber) {}
    public void returnChange(double amount) {}
    public void updateInventory(VendingMachine machine, int rackNumber) {}
    public void dispenseProduct(VendingMachine machine, int rackNumber) {}
}
