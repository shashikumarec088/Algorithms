package com.github.shashi.lld.linkedin;

public class Admin extends Person {
    public boolean blockUser(User user) {
        return false;
    }

    public boolean unblockUser(User user) {
        return false;
    }

    public boolean disablePage(CompanyPage page) {
        return false;
    }

    public boolean enablePage(CompanyPage page) {
        return false;
    }

    public boolean deleteGroup(Group group) {
        return false;
    }
}
