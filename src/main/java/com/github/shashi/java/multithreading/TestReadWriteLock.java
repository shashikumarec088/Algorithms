package com.github.shashi.java.multithreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();

        // Creating threads for reading
        Thread reader1 = new Thread(example::readData);
        Thread reader2 = new Thread(example::readData);

        // Creating threads for writing
        Thread writer1 = new Thread(example::writeData);
        Thread writer2 = new Thread(example::writeData);

        // Starting the threads
        reader1.start();
        reader2.start();
        writer1.start();
        writer2.start();

        // Joining threads to ensure main waits for their completion
        try {
            reader1.join();
            reader2.join();
            writer1.join();
            writer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ReadWriteLockExample {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void readData() {
        lock.readLock().lock();
        try {
            // Reading data
            Thread.sleep(1000);
            System.out.println("Reading data");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writeData() {
        lock.writeLock().lock();
        try {
            // Writing data
            System.out.println("Writing data");
        } finally {
            lock.writeLock().unlock(); // we must unlock the lock in finally block
        }
    }
}

