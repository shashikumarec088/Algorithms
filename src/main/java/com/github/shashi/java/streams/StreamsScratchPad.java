package com.github.shashi.java.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsScratchPad {
    public static void main(String[] args) {
        String word = "shashi kumar v k";
        Integer sortZeros =  findNthSalary(Map.of("shashi",100,"praps",150),2);
        System.out.println(sortZeros);
    }

    public static Integer[] sortZeros(Integer[] input){
        return Arrays.stream(input)
                .sorted().collect(Collectors.toList()).toArray(new Integer[input.length]);
    }

    public static Integer findNthSalary(Map<String,Integer> salaries, int n){
        return salaries.entrySet()
                .stream().collect(Collectors.groupingBy(e->e.getValue(),
                        Collectors.mapping(e->e.getKey(),Collectors.toList())))
                .entrySet().stream().sorted(Collections.reverseOrder((a,b)->b.getKey()-a.getKey())).map(e->e.getKey())
                .skip(n-1).findFirst().get();
    }

    public static Map<String,Long> deplicateChars(String words){
        return Arrays.stream(words.split(""))
                .collect(
                        Collectors.groupingBy(e->e,Collectors.counting())
                );
    }

    public static Map<Integer, List<String>> grpByLengthAndMap(List<String> words){
        return words.stream()
                .collect(Collectors.groupingBy(String::length,LinkedHashMap::new,
                        Collectors.mapping(String::toUpperCase, Collectors.toList())));
    }

    public static Map<Integer, Long> grpByLengthCustomMap(List<String> words){
        return words.stream()
                .collect(Collectors.groupingBy(String::length,LinkedHashMap::new,Collectors.counting()));
    }

    public static Map<Character, Long> getCharFreq(String word){
        return word.chars().mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,Collectors.counting()));
    }

    public static Map<String, Long> countWordsFreq(List<String> words){
        return words.stream()
                .collect(Collectors.groupingBy(c->c,Collectors.counting()));
    }

    public static Map<Integer, List<String>> grpByLength(List<String> words){
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    public static Map<Integer, Long> countByLength(List<String> words){
        return words.stream()
                .collect(Collectors.groupingBy(String::length,Collectors.counting()));
    }
}
