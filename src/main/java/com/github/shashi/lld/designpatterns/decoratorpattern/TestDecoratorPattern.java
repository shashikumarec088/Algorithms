package com.github.shashi.lld.designpatterns.decoratorpattern;

public class TestDecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new MilkCoffeeDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}

interface Coffee{
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee{

    @Override
    public String getDescription() {
        return "This is Coffee";
    }

    @Override
    public double getCost() {
        return 0.25;
    }
}

abstract class CoffeeDecorator implements Coffee{
    Coffee coffee;

    CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

class MilkCoffeeDecorator extends CoffeeDecorator{

    MilkCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription()+", with milk";
    }

    @Override
    public double getCost() {
        return super.getCost()+0.5;
    }
}