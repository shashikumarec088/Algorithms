package com.github.shashi.lld.amazonshopingsystem;

import java.util.List;

public class ShoppingCart {
    private int totalPrice;
    private List<Item> items;

    public boolean addItem(Item item) {
        return false;
    }

    public boolean removeItem(Item item) {
        return false;
    }

    public List<Item> getItems() {
        return null;
    }

    public boolean checkout() {
        return false;
    }
}