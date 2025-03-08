package com.github.shashi.java.designpatterns.singleton;

public class SingletonNonSafe {
    private static SingletonNonSafe instance;

    private SingletonNonSafe() {
    }

    public static SingletonNonSafe getInstance() {
        return instance;
    }
}
