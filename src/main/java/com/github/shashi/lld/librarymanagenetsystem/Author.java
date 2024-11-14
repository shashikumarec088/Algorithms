package com.github.shashi.lld.librarymanagenetsystem;

import java.util.List;

public class Author extends User{
    private List<Book> books;
    @Override
    public boolean resetPassword() {
        return false;
    }

}
