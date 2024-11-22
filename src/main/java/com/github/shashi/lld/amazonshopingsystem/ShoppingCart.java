package com.github.shashi.lld.amazonshopingsystem;

public class ShoppingCart {
    private int totalPrice;
    private List<Items> items;

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