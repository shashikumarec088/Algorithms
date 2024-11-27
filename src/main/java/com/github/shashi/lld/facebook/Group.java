package com.github.shashi.lld.facebook;

import java.util.List;

public class Group implements GroupFunctions {
    private int groupId;
    private String name;
    private String description;
    private byte[] coverPhoto;
    private int totalUsers;
    private boolean isPrivate;
    private List<User> users;

    public boolean addUser(User user) {
        // functionality
        return false;
    }
    public boolean deleteUser(User user) {
        // functionality
        return false;
    }
    public boolean notifyUser(User user) {
        // functionality
        return false;
    }
    public void updateDescription(String description) {}
    public void addCoverPhoto(byte[] image) {}
}