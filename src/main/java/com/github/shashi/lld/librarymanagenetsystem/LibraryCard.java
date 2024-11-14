package com.github.shashi.lld.librarymanagenetsystem;

import java.util.Date;

public class LibraryCard {
    private String cardNumber;
    private Date issued;
    private boolean active;

    public boolean isActive(){
        return active;
    }
}
