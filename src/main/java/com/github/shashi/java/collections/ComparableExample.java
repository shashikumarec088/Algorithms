package com.github.shashi.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableExample {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("shashi",30));
        persons.add(new Person("prapulla",27));
        Collections.sort(persons);
        for(Person person : persons)
            System.out.println(person);
    }
}
