package com.github.shashi.java.multithreading;

public class ThreadPriorityTest {
    public static void main(String[] args) {
        Thread lowPriorityThread = new Thread(()->{
            for(int i=0;i<5;i++)
                System.out.println("Low priority thread is running");
        });

        Thread highPriorityThread = new Thread(()->{
            for(int i=0;i<5;i++)
                System.out.println("high priority thread is running");
        });

        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.start();
        highPriorityThread.start();
    }
}
