package com.github.shashi.misc.multithreading;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        RunnableWithStop r2 = new RunnableWithStop();
        new Thread(r2).start();
        Thread.sleep(3000);
        int i = 100_00;
        r2.doStop();

    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("this is myRunnable instance with id : "+Thread.currentThread().getId());
    }
}
