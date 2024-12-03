package com.github.shashi.lld.linkedin;

import java.util.List;

public class Group {
    private int groupId;
    private String name;
    private String description;
    private int totalMembers;
    private List<User> members;

    public boolean updateDescription() {
        return false;
    }
}