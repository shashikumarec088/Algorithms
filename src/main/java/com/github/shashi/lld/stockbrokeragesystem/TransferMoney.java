package com.github.shashi.lld.stockbrokeragesystem;

import java.util.Date;

public abstract class TransferMoney {
    private int id;
    private Date creationDate;
    public int fromAccount;
    private int toAccount;

    public abstract boolean initiateTransaction();
}