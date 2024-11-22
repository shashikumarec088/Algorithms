package com.github.shashi.lld.amazonshopingsystem;

public class Admin {
    private Account account;

    public boolean blockUser(Account account) {
        return false;
    }

    public boolean addNewProductCategory(ProductCategory category) {
        return false;
    }

    public boolean modifyProductCategory(ProductCategory category) {
        return false;
    }

    public boolean deleteProductCategory(ProductCategory category) {
        return false;
    }
}