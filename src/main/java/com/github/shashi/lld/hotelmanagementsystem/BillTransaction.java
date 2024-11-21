package com.github.shashi.lld.hotelmanagementsystem;

import java.util.Date;

public abstract class BillTransaction {
    private Date creationDate;
    private double amount;
    private PaymentStatus status;

    public abstract void initiateTransaction();
}
