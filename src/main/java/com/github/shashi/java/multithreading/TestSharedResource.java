package com.github.shashi.java.multithreading;

public class TestSharedResource {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(new Producer(sharedResource));
        Thread consumer = new Thread(new Consumer(sharedResource));
        producer.start();
        consumer.start();
    }
}
class Producer implements Runnable{
    private SharedResource sharedResource;

    Producer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for(int i=0; i<5;i++)
            sharedResource.setValue(i);
    }
}

class Consumer implements Runnable{
    private SharedResource sharedResource;

    Consumer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for(int i=0; i<5;i++)
            sharedResource.getValue();
    }
}
