package com.github.shashi.java.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableInterface {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<String> task = () -> "Hello, World! from thread pool";
        Future<String> future = executor.submit(task);
        String result = null;
        try {
            result = future.get(); // Blocks until the result is available
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        executor.shutdown();
    }

}
