package com.github.shashi.java.designpatterns.singleton;

public class SingleTonBill {
    private SingleTonBill(){}
    private static class SingletonHelper{
        private static final SingleTonBill instance = new SingleTonBill();
    }
    public static SingleTonBill getInstance(){
        return SingletonHelper.instance;
    }
}
