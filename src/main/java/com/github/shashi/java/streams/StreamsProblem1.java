package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsProblem1 {
    /*
    Write a program to find the duplicate characters in list of string . Only java stream will be accepted
     */

    public static void main(String[] args) {
        String input = "shashi kumar";

        System.out.println(

        );
    }

    public List<String> findDuplicateCharacter(String input){
        Stream<String> subStrs =  Arrays.stream(input.split(""));
        Map<String,Long> counts = subStrs.collect(Collectors.groupingBy(ch ->ch,Collectors.counting()));
        List<String> res = counts.entrySet().stream().filter(elem -> elem.getValue()>1)
                .map(elem -> elem.getKey()).collect(Collectors.toList());
        return res;
    }
}
