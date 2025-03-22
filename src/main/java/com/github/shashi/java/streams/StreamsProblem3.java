package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsProblem3 {
    /*
    You have an arrays of zeros (0) and (1) keep all zeros at the left hand side and all 1 to right hand side ?
       e.g Integer array[] =[1,1,0,1,0] -> output : [0,0,1,1,1]
     */

    public Integer[] seperateOnes(Integer[] input){
        Stream<Integer> stream = Arrays.stream(input);
        List<Integer> res = stream.sorted().collect(Collectors.toList());
        return res.toArray(new Integer[input.length]);
    }
}
