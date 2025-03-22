package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsProblem9 {
    /*
    Write a program to find longest string in a given array.
     */

    public  String findLongestString(String[] strs){
        return Arrays.stream(strs)
                .reduce((w1,w2)-> w1.length()>w2.length()?w1:w2)
                .orElse("");
    }

    public  String findLongestNthString(String[] strs, int n){
        return Arrays.stream(strs)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(n-1)
                .findFirst().orElse("");
    }
}
