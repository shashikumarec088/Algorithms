package com.github.shashi.lld.librarymanagenetsystem;

import java.util.Date;
import java.util.List;

public interface Search {
    // Interface method (does not have a body)
    public List<Book> searchByTitle(String title);
    public List<Book> searchByAuthor(String author);
    public List<Book> searchBySubject(String subject);
    public List<Book> searchByPublicationDate(Date publishDate);
}
