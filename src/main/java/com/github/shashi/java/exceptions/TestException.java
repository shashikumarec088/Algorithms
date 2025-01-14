package com.github.shashi.java.exceptions;

public class TestException {
    public static void main(String[] args) {
        try {
            validateAge(15);
        } catch (UnderAgeException | ArithmeticException e) {
            e.printStackTrace();
        }
    }

    static void validateAge(int age) throws UnderAgeException{
        if(age<18){
            throw new UnderAgeException("age must be above 18");
        }
    }
}
