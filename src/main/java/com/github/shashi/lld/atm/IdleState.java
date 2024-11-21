package com.github.shashi.lld.atm;

public class IdleState extends ATMState {

    @Override
    public void insertCard(ATM atm, ATMCard card) {

    }

    @Override
    public void authenticatePin(ATM atm, ATMCard card, int pin) {

    }

    @Override
    public void selectOperation(ATM atm, ATMCard card, TransactionType tType) {

    }

    @Override
    public void cashWithdrawal(ATM atm, ATMCard card, int withdrawAmount) {

    }

    @Override
    public void displayBalance(ATM atm, ATMCard card) {

    }

    @Override
    public void transferMoney(ATM atm, ATMCard card, int accountNumber, int transferAmount) {

    }

    @Override
    public void returnCard() {

    }

    @Override
    public void exit(ATM atm) {

    }
}