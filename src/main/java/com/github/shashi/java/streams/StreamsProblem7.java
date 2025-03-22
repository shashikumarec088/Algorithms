package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsProblem7 {
    /*
    Write a program to find second highest element from an array ,
    Array can contain duplicate elements . Solve it by stream java 8.
     */

    public int findNthHighest(int[] input, int n){
        return Arrays.stream(input).boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(n-1)
                .findFirst()
                .orElse(-1);
    }
}
