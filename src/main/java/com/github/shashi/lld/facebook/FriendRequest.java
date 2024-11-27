package com.github.shashi.lld.facebook;

import java.util.Date;

public class FriendRequest {
    private User recipent;
    private User sender;
    private FriendInviteStatus status;
    private Date requestSent;
    private Date requestStatusModified;

    public boolean acceptRequest(User user) {
        return false;
    }

    public boolean rejectRequest(User user) {
        return false;
    }
}