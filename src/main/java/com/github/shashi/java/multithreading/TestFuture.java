package com.github.shashi.java.multithreading;

import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // the <?> represents that there is no return value ( it internally uses Runnable.run() )
        Future<?> futureObj = executorService.submit(()->{
            try{
                Thread.sleep(5000);
                System.out.println("This is thread inside thread pool");
            }catch (Exception e){

            }
        });
        System.out.println(futureObj.isDone());
        try{
            futureObj.get(2, TimeUnit.SECONDS);  // wait only for 2 seconds.
        }catch (TimeoutException te){
            System.out.println("System timed out after 2 seconds !!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            futureObj.get();// waits here until the thread associated with futureObj is done.
        }catch (Exception e){}
        System.out.println("Is task completed ? : "+futureObj.isDone());
        System.out.println("Is task canceled ? : "+futureObj.isCancelled());
        executorService.shutdown();
    }
}
