package com.github.shashi.lld.stackoverflow;

import java.util.Date;
import java.util.List;

public class Question{
    private int id;
    private String title;
    private String content;
    private User createdBy;
    private int upvotes;
    private int downvotes;
    private int viewCount;
    private int score;
    private int voteCount;
    private Date creationDate;
    private Date modificationDate;
    private QuestionStatus status;
    private ClosingDetail closingReason;
    private Bounty bounty;

    private List<Tag> tags;
    private List<Comment> comments;
    private List<Answer> answers;
    private List<User> followers;

    public void addComment(Comment comment) {

    }

    public void addBounty(Bounty bounty) {

    }
}