package com.github.shashi.lld.linkedin;

import java.util.List;

public class Message {
    private int messageId;
    private User sender;
    private List<User> recipients;
    private String text;
    private List<Byte> media;

    public boolean addRecipients(List<User> recipients) {
        return false;
    }
}
