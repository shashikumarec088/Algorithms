package com.github.shashi.java.multithreading;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();   // thread is created here.
        MyThread thread2 = new MyThread();

        thread1.start(); // Start thread 1
        thread2.start(); // Start thread 2


    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        // Code to be executed in this thread
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

