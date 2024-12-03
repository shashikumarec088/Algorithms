package com.github.shashi.lld.linkedin;

import java.util.Date;
import java.util.List;

public class User extends Person {
    private int userId;
    private Date dateOfJoining;
    private Profile profile;
    private List<User> connections;
    private List<User> followsUsers;
    private List<CompanyPage> followCompanies;
    private List<Group> joinedGroups;
    private List<CompanyPage> createdPages;
    private List<Group> createdGroups;

    public boolean sendMessage(Message message) {
        return false;
    }

    public boolean sendInvite(ConnectionInvitation invite) {
        return false;
    }

    public boolean cancelInvite(ConnectionInvitation invite) {
        return false;
    }

    public boolean createPage(CompanyPage page) {
        return false;
    }

    public boolean deletePage(CompanyPage page) {
        return false;
    }

    public boolean createGroup(Group group) {
        return false;
    }

    public boolean deleteGroup(Group group) {
        return false;
    }

    public boolean createPost(Post post) {
        return false;
    }

    public boolean deletePost(Post post) {
        return false;
    }

    public boolean createComment(Comment comment) {
        return false;
    }

    public boolean deleteComment(Comment comment) {
        return false;
    }

    public boolean react(Post post) {
        return false;
    }

    public boolean requestRecommendation(User user) {
        return false;
    }

    public boolean acceptRecommendation(User user) {
        return false;
    }

    public boolean applyForJob(Job job) {
        return false;
    }
}