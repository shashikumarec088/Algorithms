package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StreamsProblem9Test {
    private StreamsProblem9 streamsProblem9;


    @Before
    public void setUp(){
        streamsProblem9 = new StreamsProblem9();
    }


    @Test
    public void testLongestString(){
        String[] arr = {"shashi","kumar","vk"};
        String res = streamsProblem9.findLongestString(arr);
        String ans = Arrays.stream(arr)
                        .sorted((a,b)->b.length()-a.length())
                                .findFirst().orElse("");
        assertEquals(ans,res);
    }

    @Test
    public void testNthLongestString(){
        String[] arr = {"shashi","kumar","vk"};
        String res = streamsProblem9.findLongestNthString(arr,2);
        String ans = Arrays.stream(arr)
                .sorted((a,b)->b.length()-a.length())
                .skip(1)
                .findFirst().orElse("");
        assertEquals(ans,res);
    }


}
