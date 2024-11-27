package com.github.shashi.lld.facebook;

import java.util.Date;

public class User extends Person implements PageFunctionsByUser{
    private int userId;
    private String name;
    private Date dateOfJoining;
    // The following lists contain the pages and groups that a user is admin of
    private List<Pages> pagesAdmin;
    private List<Groups> groupsAdmin;

    private Profile profile;

    public boolean sendMessage(Message message) {
        return false;
    }

    public boolean sendRecommendation(Page page, Group group, User user) {
        return false;
    }

    public boolean sendFriendRequest(User user) {
        return false;
    }

    public boolean unfriendUser(User user) {
        return false;
    }

    public boolean blockUser(User user) {
        return false;
    }

    public boolean followUser(User user) {
        return false;
    }

    // The functions of the different interfaces will also be present here
    public Page createPage(String name) {
        // functionality
        return null;
    }
    public void likePage(Page page){
        // functionality
    }
    public void followPage(Page page){
        // functionality
    }
    public void unLikePage(Page page){
        // functionality
    }
    public void unFollowPage(Page page){
        // functionality
    }
    public Page sharePage(Page page){
        // functionality
        return page;
    }
}