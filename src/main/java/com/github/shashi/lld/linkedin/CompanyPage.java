package com.github.shashi.lld.linkedin;

import java.util.List;

public class CompanyPage {
    private int pageId;
    private String name;
    private String description;
    private String type;
    private int companySize;
    private User createdBy;
    private List<Job> jobs;

    public boolean createJobPosting() {
        return false;
    }

    public boolean deleteJobPosting(Job job) {
        return false;
    }
}