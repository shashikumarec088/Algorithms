package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsProblem10 {
    /*
    Write a program to find longest string in a given array.
     */

    public List<Integer> findAllStartWith2(int[] nums){
        return Arrays.stream(nums)
                .boxed()
                .filter(e-> e.toString().startsWith("2"))
                .collect(Collectors.toList());
    }


}
