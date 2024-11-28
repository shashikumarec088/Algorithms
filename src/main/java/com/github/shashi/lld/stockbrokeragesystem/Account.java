package com.github.shashi.lld.stockbrokeragesystem;

public abstract class Account {
    private String id;
    private String password;
    private String name;
    private AccountStatus status;
    private Address address;
    private String email;
    private String phone;

    public abstract boolean resetPassword();
}
