package com.github.shashi.java.threads;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        myThread1.start();
        MyThread myThread2 = new MyThread();
        myThread2.start();

        // creating the thread using java 8 lambda

        Thread lambdaThread = new Thread(() -> {
            System.out.println("Lambda Thread running  :: "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    static class MyThread extends Thread{
        public void run(){
            System.out.println("MyThread running  :: "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
