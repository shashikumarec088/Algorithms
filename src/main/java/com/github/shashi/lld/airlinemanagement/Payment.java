package com.github.shashi.lld.airlinemanagement;

import java.util.Date;

public abstract class Payment {
    private int paymentId;
    private double amount;
    private PaymentStatus status;
    private Date timestamp;

    public abstract boolean makePayment();
}
