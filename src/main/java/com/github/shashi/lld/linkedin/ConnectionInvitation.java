package com.github.shashi.lld.linkedin;

import java.util.Date;

public class ConnectionInvitation {
    private User sender;
    private User recipients;
    private Date dateCreated;
    private ConnectionInviteStatus status;

    public boolean acceptConnection() {
        return false;
    }

    public boolean rejectConnection() {
        return false;
    }
}