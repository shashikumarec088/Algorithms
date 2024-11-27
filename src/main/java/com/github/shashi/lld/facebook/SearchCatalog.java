package com.github.shashi.lld.facebook;

import java.util.HashMap;
import java.util.List;

public class SearchCatalog implements Search {
    HashMap<String, List<User>> userNames;
    HashMap<String, List<Group>> groupNames;
    HashMap<String, List<Page>> pageTitles;
    HashMap<String, List<Post>> posts;

    public boolean addNewUser(User user) {
        return false;
    }
    public boolean addNewGroup(Group group) {
        return false;
    }
    public boolean addNewPage(Page page) {
        return false;
    }
    public boolean addNewPost(Post post) {
        return false;
    }

    public List<User> searchUsers(String name) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Group> searchGroups(String name) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Page> searchPages(String name) {
        // functionality
        return java.util.Collections.emptyList();
    }

    public List<Post> searchPosts(String keywords) {
        // functionality
        return java.util.Collections.emptyList();
    }
}