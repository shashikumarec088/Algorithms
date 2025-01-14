package com.github.shashi.java.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class createStream {
    public static void main(String[] args) {
        Stream<Integer> intStream = Stream.of(1,2,3,4);
        Integer[] ints = {1,2};
        Stream<Integer> arrStream =  Arrays.stream(ints);
        arrStream.forEach(System.out::println);
        Stream<Double> randStream =  Stream.generate(Math::random).limit(10);
        randStream.forEach(System.out::println);
        // using stream builder
        Stream<String> buildStream = Stream.<String>builder().add("shashi")
                                .add("kumar").build();
        // map function
        Stream<String> streamUpper = buildStream.map(String::toUpperCase);
        //streamUpper.peek(System.out::println);
        // sorting the stream
        Stream<String> sortedStream = streamUpper.sorted();

        // reduce stream;
        int sum = intStream.reduce(0,Integer::sum);
        boolean anyMatch = Stream.of(1,2,3,4,5).anyMatch(x-> x%2==0);
        System.out.println("sum of steam "+sum);
        System.out.println("any match in stream "+anyMatch);

        // creating map
        Map<Integer,String > map =  Stream.of("s1","s22").collect(Collectors.toMap(String::length, Function.identity()));
        System.out.println(map);
        String singleString = Stream.of("1","2").collect(Collectors.joining(",","[","]"));
        System.out.println(singleString);
        Stream<Person> personStream = Stream.of(new Person("shashi"), new Person("prapulla"),
                new Person("shashi"), new Person("prapulla"));
        Map<String, List<Person>> ans=  personStream.collect(Collectors.groupingBy(Person::getName));
        System.out.println(ans);

        // custom combiner in maps
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");

        // Custom collector to map strings to their lengths
        Map<String, Integer> stringLengthMap = strings.stream().collect(
                HashMap::new,                          // Supplier: Create a new HashMap
                (map1, str1) -> map1.put(str1, str1.length()), // Accumulator: Add string and its length to the map
                Map::putAll                            // Combiner: Merge two maps
        );

        Optional<String> optString = Optional.empty();
        System.out.println(optString.isPresent());
    }
}
class Person{

    Person(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
