package com.github.shashi.lld.linkedin;

import java.util.List;

public class Post {
    private int postId;
    private User postOwner;
    private String text;
    private List<Byte> media;
    private int totalReacts;
    private int totalShares;
    private List<Comment> comments;

    public boolean updateText() {
        return false;
    }
}