package com.github.shashi.java.designpatterns.singleton;

public enum SingleTonEnum {
    INSTANCE;
    /*
    create private final variables and initialize them in the constructor
    define the methods to return those
     */

    private final Object obj;
    SingleTonEnum() {
        this.obj = new Object();
        System.out.println("Singleton using Enum initialize singleton objects");
    }

    public Object getObj() {
        return obj;
    }
}
