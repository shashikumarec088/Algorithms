package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StreamsProblem10Test {
    private StreamsProblem10 streamsProblem10;


    @Before
    public void setUp(){
        streamsProblem10 = new StreamsProblem10();
    }


    @Test
    public void testStartingWith2(){
        int[] arr = {1,2,3,23,44,333};
        List<Integer> exp = Arrays.asList(2,23);
        List<Integer> res = streamsProblem10.findAllStartWith2(arr);
        assertEquals(exp,res);
    }



}
