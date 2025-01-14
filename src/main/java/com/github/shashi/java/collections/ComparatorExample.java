package com.github.shashi.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Kshashi",30));
        persons.add(new Person("prapulla",27));
        Collections.sort(persons,new NameComparator());
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.headMap(1);
        for(Person person : persons)
            System.out.println(person);
    }
}
