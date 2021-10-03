package com.github.shashi.misc.multithreading;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                c1.inc();
            }
            System.out.println(c1.getCount());
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                c1.inc();
            }
            System.out.println(c1.getCount());
        });
        t1.start();
        t2.start();


    }
}

class Counter{
    long count = 0;
    public void Counter(){}

    public void inc(){
        count++;
    }
    public long getCount(){
        return count;
    }
}
