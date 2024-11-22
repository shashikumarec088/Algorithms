package com.github.shashi.lld.amazonshopingsystem;

import java.util.List;

public class Account {
    private String userName;
    private String password;
    private String name;
    private List<Address> shippingAddress;
    private AccountStatus status;
    private String email;
    private String phone;

    private List<CreditCard> creditCards;
    private List<ElectronicBankTransfer> bankAccounts;

    public Address getShippingAddress() {
        return null;
    }

    public boolean addProduct(Product product) {
        return false;
    }

    public boolean addProductReview(ProductReview review, Product product) {
        return false;
    }

    public boolean deleteProduct(Product product) {
        return false;
    }

    public boolean deleteProductReview(ProductReview review, Product product) {
        return false;
    }

    public boolean resetPassword() {
        return false;
    }
}