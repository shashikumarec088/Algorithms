package com.github.shashi.lld.linkedin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SearchCatalog implements Search {
    private HashMap<String, List<User>> users;
    private HashMap<String, List<CompanyPage>> companies;
    private HashMap<String, List<Group>> groups;
    private HashMap<String, List<Job>> jobs;

    public void addNewUser(User user) {

    }

    public void addNewCompany(CompanyPage company) {

    }

    public void addNewGroup(Group group) {

    }

    public void addNewJob(Job job) {

    }

    public List<User> searchUser(String name) {
        // functionality
        return null;
    }

    public List<CompanyPage> searchCompany(String name) {
        // functionality
        return null;
    }

    @Override
    public List<Group> searchGroup(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<Job> searchJob(String title) {
        return Collections.emptyList();
    }
}