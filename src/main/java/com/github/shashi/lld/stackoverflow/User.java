package com.github.shashi.lld.stackoverflow;

import java.util.List;

public class User {
    private int reputationPoints;
    private Account account;
    private List<Badge> badges;

    public boolean createQuestion(Question question) {
        return false;
    }

    public boolean addAnswer(Question question, Answer answer) {
        return false;
    }

    public boolean createComment(Comment comment) {
        return false;
    }

    public boolean createTag(Tag tag) {


        return false;
    }

    public void flagQuestion(Question question) {
    }

    public void flagAnswer(Answer answer) {

    }

    public void upvote(int id) {

    }

    public void downvote(int id) {

    }

    public void voteToCloseQuestion(Question question) {

    }

    public void voteToDeleteQuestion(Question question) {

    }

    public void acceptAnswer(Answer answer) {

    }
}
