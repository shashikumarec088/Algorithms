package com.github.shashi.lld.hotelmanagementsystem;

import java.util.Date;

public abstract class Notification {
    private int notificationId;
    // The Date data type represents and deals with both date and time.
    private Date createdOn;
    private String content;

    public abstract void sendNotification(Person person);
}