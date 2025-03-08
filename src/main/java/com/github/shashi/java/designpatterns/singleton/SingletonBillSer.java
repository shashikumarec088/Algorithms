package com.github.shashi.java.designpatterns.singleton;

import java.io.Serializable;

public class SingletonBillSer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static volatile boolean isInstanceCreated = false;
    private static SingletonBillSer instance;

    private SingletonBillSer() {
        if(isInstanceCreated) {
            throw new RuntimeException("Cannot create instance. Please use getInstance() method.");
        }
        isInstanceCreated = true;
    }

    public static SingletonBillSer getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final SingletonBillSer instance = new SingletonBillSer();
    }

    protected Object readResolve() {
        return getInstance();
    }
}
