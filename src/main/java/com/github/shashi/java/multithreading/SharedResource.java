package com.github.shashi.java.multithreading;

public class SharedResource {
    private int value;
    private boolean isValueSet=false;

    public synchronized void setValue(int value){
        while(isValueSet){
            try{
                wait();
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        this.value = value;
        isValueSet = true;
        System.out.println("Produced "+value);
        notify();
    }

    public synchronized  int getValue(){
        while (!isValueSet){
            try{
                wait();
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
        isValueSet = false;
        System.out.println("Consumed "+value);
        notify();
        return value;
    }
}
