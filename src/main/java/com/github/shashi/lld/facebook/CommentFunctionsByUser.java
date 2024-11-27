package com.github.shashi.lld.facebook;

public interface CommentFunctionsByUser {
    public Comment createComment(Post post, String content);
    public void likeComment(Comment comment);
}