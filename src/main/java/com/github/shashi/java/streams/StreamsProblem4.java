package com.github.shashi.java.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsProblem4 {
    /*
    Find Nth salary in given map . Only Java 8 stream solution will be accepted
     */

    public int findNthSalary(Map<String,Integer> map, int n){
                Map<Integer,List<String>> res = map.entrySet().stream()
                        .collect(Collectors.groupingBy(a->a.getValue(),
                                Collectors.mapping(Map.Entry::getKey,Collectors.toList())));
                return res.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                        .collect(Collectors.toList()).get(n-1).getKey();
    }

    public  List<String> findNthSalaryNames(Map<String,Integer> map, int n){
        Map<Integer,List<String>> res = map.entrySet().stream()
                .collect(Collectors.groupingBy(a->a.getValue(),
                        Collectors.mapping(a->a.getKey(),Collectors.toList())));
        return res.entrySet().stream()
                .sorted((a,b)->b.getKey()-a.getKey())
                .collect(Collectors.toList()).get(n-1).getValue();
    }

}
