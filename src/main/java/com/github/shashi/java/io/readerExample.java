package com.github.shashi.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class readerExample {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine().trim());
        List<Integer> list = Stream.of(reader.readLine().replaceAll("\\s+$","")
                .split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(list);
    }
}
