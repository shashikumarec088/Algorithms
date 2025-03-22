package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsProblem2 {
    /*
    Find the duplicate element and its occurrence in a given string ? Only java stream will be accepted ?
     */

    public Map<String,Long> findDuplicateCharacterAndOccurance(String input){
        Stream<String> stream = Arrays.stream(input.split(""));
        Map<String,Long> res = stream.collect(Collectors.groupingBy(str -> str,Collectors.counting()));
        return res;
    }
}
