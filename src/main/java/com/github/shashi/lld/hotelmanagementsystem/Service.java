package com.github.shashi.lld.hotelmanagementsystem;

import java.util.Date;

public abstract class Service {
    private Date issueAt;

    public boolean addInvoiceItem(Invoice invoice) {
        return false;
    }
}