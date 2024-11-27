package com.github.shashi.lld.stackoverflow;

import java.util.Date;
import java.util.List;

public class Answer {
    private int id;
    private String content;
    private int flagCount;
    private int voteCount;
    private int upvotes;
    private int downvotes;
    private boolean isAccepted;
    private Date creationTime;
    private User postedBy;

    private List<Comment> comments;
    private List<User> followers;

    public void addComment(Comment comment) {

    }
}
