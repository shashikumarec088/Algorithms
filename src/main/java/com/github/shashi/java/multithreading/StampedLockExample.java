package com.github.shashi.java.multithreading;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private int count = 0;
    private final StampedLock lock = new StampedLock();

    // Write method
    public void increment() {
        long stamp = lock.writeLock();  // Acquire write lock
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
        } finally {
            lock.unlockWrite(stamp);  // Release write lock
        }
    }

    // Read method using read lock
    public void readCount() {
        long stamp = lock.readLock();  // Acquire read lock
        try {
            System.out.println(Thread.currentThread().getName() + " read count: " + count);
        } finally {
            lock.unlockRead(stamp);  // Release read lock
        }
    }

    // Read method using optimistic read lock
    public void optimisticReadCount() {
        long stamp = lock.tryOptimisticRead();  // Try optimistic read lock
        int currentCount = count;  // Read without lock
        if (!lock.validate(stamp)) {  // Check if write occurred during read
            stamp = lock.readLock();  // Reacquire read lock if data changed
            try {
                currentCount = count;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " optimistically read count: " + currentCount);
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockExample example = new StampedLockExample();

        Thread writer = new Thread(() -> example.increment());
        Thread reader1 = new Thread(() -> example.readCount());
        Thread reader2 = new Thread(() -> example.optimisticReadCount());

        writer.start();
        reader1.start();
        reader2.start();

        writer.join();
        reader1.join();
        reader2.join();
    }
}
