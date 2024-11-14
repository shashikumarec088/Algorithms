package com.github.shashi.lld.librarymanagenetsystem;

import java.util.Date;

public abstract class Notification {
    private String notificationId;
    private Date creationDate;
    private String content;
    private BookLending bookLending;
    private BookReservation bookReservation;

    public abstract boolean sendNotification();
}