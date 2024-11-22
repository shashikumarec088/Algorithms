package com.github.shashi.lld.amazonshopingsystem;

public class AuthenticatedUser extends Customer {
    private Account account;
    private Order order;

    public OrderStatus placeOrder(Order order) {
        return null;
    }

    public ShoppingCart getShoppingCart() {
        // functionality
        return null;
    }
}