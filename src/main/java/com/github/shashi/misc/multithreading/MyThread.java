package com.github.shashi.misc.multithreading;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("this is MyThread instance with id :"+Thread.currentThread().getId());
    }
}
