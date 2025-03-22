package com.github.shashi.java.multithreading;

public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable);  // thread is created here.
        Thread thread2 = new Thread(myRunnable);

        thread1.start(); // Start thread 1
        thread2.start(); // Start thread 2

    }
}
class MyRunnable implements Runnable {
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

