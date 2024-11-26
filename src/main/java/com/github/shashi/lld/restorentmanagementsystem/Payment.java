package com.github.shashi.lld.restorentmanagementsystem;

import java.util.Date;

public abstract class Payment {
    private int paymentID;
    private Date creationDate;
    private double amount;
    private PaymentStatus status;

    public abstract void initiateTransaction();
}
