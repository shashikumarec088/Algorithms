package com.github.shashi.lld.airlinemanagement;

import java.util.Date;

public abstract class Notification {
    private int notificationId;
    private Date createdOn;
    private String content;

    public abstract void sendNotification(Account account);
}
