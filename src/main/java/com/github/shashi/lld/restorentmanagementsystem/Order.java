package com.github.shashi.lld.restorentmanagementsystem;

import java.util.Date;

public class Order {
    private int OrderID;
    private OrderStatus status;
    private Date creationTime;
    private Meal[] meals;
    private Table table;
    private Waiter waiter;
    private Chef chef;

    public boolean addMeal(Meal meal) {
        return false;
    }

    public boolean removeMeal(Meal meal) {
        return false;
    }
}
