package com.github.shashi.java.interfaces;

import java.util.function.Consumer;

interface A {
    default void show() {
        System.out.println("A");
    }
}
interface B {
    default void show() {
        System.out.println("B");
    }
}

public class C implements A, B {
    @Override
    public void show() {
        A.super.show();  // or B.super.show();
    }

    public static void main(String[] args) {
        Consumer<String> printMessage = message -> System.out.println("Message: " + message);
        // Define the second Consumer: Converts the message to uppercase and prints it
        Consumer<String> printUpperCaseMessage = message -> System.out.println("Uppercase Message: " + message.toUpperCase());
        // Chain the Consumers using andThen
        Consumer<String> combinedConsumer = printMessage.andThen(printUpperCaseMessage);
        // Use the combined Consumer
        combinedConsumer.accept("Hello, World!");
    }
}

