package com.github.shashi.lld.facebook;

public class Account {
    private String accountId;
    private String password;
    private String username;
    private String email;
    private AccountStatus status;

    public boolean resetPassword() {
        return false;
    }
}