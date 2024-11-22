package com.github.shashi.lld.amazonshopingsystem;

public class Product {
    private String productId;
    private String name;
    private String description;
    private byte[] image;
    private double price;
    private ProductCategory category;
    private List<ProductReview> reviews;
    private int availableItemCount;
    private Account account;

    public int getAvailableCount() {
        return 0;
    }

    public int updateAvailableCount() {
        return 0;
    }

    public boolean updatePrice(double newPrice) {
        return false;
    }
}