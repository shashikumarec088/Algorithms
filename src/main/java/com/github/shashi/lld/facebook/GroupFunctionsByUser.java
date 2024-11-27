package com.github.shashi.lld.facebook;

public interface GroupFunctionsByUser {
    public Group createGroup(String name);
    public void joinGroup(Group group);
    public void leaveGroup(Group group);
    public void sendGroupInvite(Group group);
}
