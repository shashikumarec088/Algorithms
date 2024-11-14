package com.github.shashi.lld.librarymanagenetsystem;

public abstract class User {
    private String id;
    private String password;
    private AccountStatus status;
    private Person person;
    private LibraryCard card;

    public abstract boolean resetPassword();
}