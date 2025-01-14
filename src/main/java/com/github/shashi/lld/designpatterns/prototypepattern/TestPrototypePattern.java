package com.github.shashi.lld.designpatterns.prototypepattern;

import java.util.HashMap;
import java.util.Map;

public class TestPrototypePattern {
    public static void main(String[] args) {
        ShapeRegistry shapeRegistry = new ShapeRegistry();

        // Register prototypes
        shapeRegistry.registerShape("Big Circle", new Circle(10));
        shapeRegistry.registerShape("Small Rectangle", new Rectangle(5, 10));

        // Clone and use shapes
        Shape clonedCircle = shapeRegistry.getShape("Big Circle");
        clonedCircle.draw();

        Shape clonedRectangle = shapeRegistry.getShape("Small Rectangle");
        clonedRectangle.draw();
    }
}
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

// Step 2: Implement concrete classes
class Circle implements Shape {
    private String type;
    private int radius;

    public Circle(int radius) {
        this.type = "Circle";
        this.radius = radius;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}

class Rectangle implements Shape {
    private String type;
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.type = "Rectangle";
        this.width = width;
        this.height = height;
    }
    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle with width " + width + " and height " + height);
    }
}

// Step 3: Create a Prototype Registry
class ShapeRegistry {
    private Map<String, Shape> shapes = new HashMap<>();

    public void registerShape(String key, Shape shape) {
        shapes.put(key, shape);
    }

    public Shape getShape(String key) {
        return shapes.get(key).clone();
    }
}
