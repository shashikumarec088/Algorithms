package com.github.shashi.java.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// 1. Define the Custom Annotation
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface Positive {
    String message() default "Value must be positive";
}

// 2. Implement the Custom Validator
class Validator {
    public static void validate(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Positive.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof Integer && (Integer) value <= 0) {
                    Positive annotation = field.getAnnotation(Positive.class);
                    throw new Exception(annotation.message());
                }
            }
        }
    }
}

// 3. Apply the Custom Annotation
class Product {
    private String name;

    @Positive(message = "Price must be positive")
    private Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}

// 4. Use the Validator
public class Main {
    public static void main(String[] args) {
        Product product = new Product("Laptop", -500);

        try {
            Validator.validate(product);  // Validate the object
            System.out.println("Product is valid.");
        } catch (Exception e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
