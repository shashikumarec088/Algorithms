package com.github.shashi.lld.carrentalsystem;

import java.util.Date;

public abstract class Payment {
    // Data members
    private double amount;

    // The Date datatype represents and deals with both date and time.
    private Date timestamp;
    private PaymentStatus status;

    public abstract boolean makePayment();
}
