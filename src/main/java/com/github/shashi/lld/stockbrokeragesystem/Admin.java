package com.github.shashi.lld.stockbrokeragesystem;

public class Admin extends Account {
    public boolean blockMember() {
        return false;
    }

    public boolean unblockMember() {
        return false;
    }

    public boolean cancelMembership() {
        return false;
    }

    public boolean resetPassword(){
        // definition
        return false;
    }
}