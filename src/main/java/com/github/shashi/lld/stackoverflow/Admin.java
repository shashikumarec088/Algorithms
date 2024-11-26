package com.github.shashi.lld.stackoverflow;

public class Admin extends User {
    public boolean blockUser(User user) {
        return false;
    }

    public boolean unblockUser(User user) {


        return false;
    }

    public void awardBadge(User user, Badge badge) {
    }
}
