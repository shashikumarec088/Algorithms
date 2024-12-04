package com.github.shashi.lld.restorentmanagementsystem;

import java.util.List;

public class Menu {
    private int menuID;
    private String title;
    private String description;
    private double price;
    private List<MenuSection> menuSections;

    public boolean addMenuSection(MenuSection menuSection) {
        return false;
    }

    public boolean print() {
        return false;
    }
}