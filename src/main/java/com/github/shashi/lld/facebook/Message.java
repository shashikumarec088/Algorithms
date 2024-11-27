package com.github.shashi.lld.facebook;

import java.util.List;

public class Message {
    private int messageId;
    private User sender;
    private String content;
    private List<User> recipent;
    private List<byte[]> multimedia;

    public boolean addRecipent(List<User> users) {
        return false;
    }
}
