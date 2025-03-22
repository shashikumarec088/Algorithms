package com.github.shashi.java.multithreading;

import java.util.concurrent.Semaphore;
public class FairCountingSemaphoreExample {
    // Semaphore with 3 permits, and fairness set to true
    private static final Semaphore semaphore = new Semaphore(3, true); // Fair Semaphore

    public static void main(String[] args) throws InterruptedException {
        // Runnable task that simulates resource access
        Runnable task = () -> {
            try {
                // Trying to acquire a permit
                System.out.println(Thread.currentThread().getName() + " is trying to acquire a permit.");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired a permit.");

                // Simulate work (resource usage)
                Thread.sleep(2000);  // Simulating some work with the resource

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Releasing the permit after the work is done
                System.out.println(Thread.currentThread().getName() + " releasing a permit.");
                semaphore.release();
            }
        };
        // Create 5 threads, but only 3 can run at a time due to the semaphore's limit
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
