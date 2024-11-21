package com.github.shashi.lld.atm;

public class CashWithdrawalState extends ATMState {

    @Override
    public void insertCard(ATM atm, ATMCard card) {
        // definition
    }

    @Override
    public void authenticatePin(ATM atm, ATMCard card, int pin) {
        // definition
    }

    @Override
    public void selectOperation(ATM atm, ATMCard card, TransactionType tType) {
        // definition
    }

    @Override
    public void cashWithdrawal(ATM atm, ATMCard card, int withdrawAmount) {
        // definition
    }

    @Override
    public void displayBalance(ATM atm, ATMCard card) {
        // definition
    }

    @Override
    public void transferMoney(ATM atm, ATMCard card, int accountNumber, int transferAmount) {
        // definition
    }

    @Override
    public void returnCard() {
        // definition
    }

    @Override
    public void exit(ATM atm) {
        // definition
    }
}