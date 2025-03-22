package com.github.shashi.java.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsProblem6 {
    /*
    Write a program to find first non repeat element from a given string by Java Streams.
     */

    public  String firstNonRepeatCharacter(String s){
                 return Arrays.stream(s.split(""))
                        .collect(
                                Collectors.groupingBy(
                                        e->e,
                                        LinkedHashMap::new,
                                        Collectors.counting()
                                )
                        ).entrySet()
                         .stream()
                         .filter(e->e.getValue()==1)
                         .map(Map.Entry::getKey)
                         .findFirst().orElse("");
    }


}
