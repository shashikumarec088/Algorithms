package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsProblem8 {
    /*
    Write a program to find first repeat element/character from a given string by Java Streams.
     */

    public  String testFirstRepeatChar(String s){
        return Arrays.stream(s.split(""))
                .collect(
                        Collectors.groupingBy(
                                e->e,
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                ).entrySet()
                .stream()
                .filter(e->e.getValue()>1)
                .map(Map.Entry::getKey)
                .findFirst().orElse("");
    }
}
