package com.github.shashi.lld.meetingscheduler;

import java.util.Date;

public class Notification {
    private int notificationId;
    private String content;
    private Date creationDate;

    public boolean sendNotification(User user) {
        return false;
    }

    public boolean cancelNotification(User user) {
        return false;
    }
}