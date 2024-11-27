package com.github.shashi.lld.facebook;

public interface PostFunctionsByUser {
    public Post createPost(String content);
    public Post sharePost(Post post);
    public void commentOnPost(Post post);
    public void likePost(Post post);
}