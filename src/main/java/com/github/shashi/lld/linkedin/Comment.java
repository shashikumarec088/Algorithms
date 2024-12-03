package com.github.shashi.lld.linkedin;

import java.util.List;

public class Comment {
    private int commentId;
    private User commentOwner;
    private String text;
    private int totalReacts;
    private List<Comment> comments;

    public boolean updateText() {
        return false;
    }
}