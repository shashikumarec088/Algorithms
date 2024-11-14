package com.github.shashi.lld.librarymanagenetsystem;

import java.util.Date;

public class BookLending {
    private String itemId;
    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private String memberId;
    private BookReservation bookReservation;
    private User user;

    public static boolean lendBook(String bookItemId, String memberId) {
        return false;
    }

    public static BookLending fetchLendingDetails(String bookItemId) {
        return null;
    }
}
