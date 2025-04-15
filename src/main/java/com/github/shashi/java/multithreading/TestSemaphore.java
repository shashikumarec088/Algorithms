package com.github.shashi.java.multithreading;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    private static final Semaphore semaphores = new Semaphore(3,true);
    public static void main(String[] args) {
        Runnable r1 = () ->{
            System.out.println(Thread.currentThread().getName() + "started to acquire lock");
            try {
                semaphores.acquire();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "releasing the lock");
                semaphores.release();
            }
        };

        for(int i=0; i<5;i++)
            new Thread(r1).start();
    }
}
