package com.github.shashi.misc.multithreading;

public class RunnableWithStop implements Runnable{
    private boolean isStop = false;

    public synchronized void doStop(){
        this.isStop = true;
    }

    private synchronized boolean keepRunning(){
        return this.isStop == false;
    }
    @Override
    public void run(){
    while (keepRunning()){
        System.out.println("keep running");
    }
    }
}
