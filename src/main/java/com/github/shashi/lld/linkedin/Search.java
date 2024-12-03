package com.github.shashi.lld.linkedin;

import java.util.List;

public interface Search {
    // Interface method (does not have a body)
    public List<User> searchUser(String name);
    public List<CompanyPage> searchCompany(String name);
    public List<Group> searchGroup(String name);
    public List<Job> searchJob(String title);
}