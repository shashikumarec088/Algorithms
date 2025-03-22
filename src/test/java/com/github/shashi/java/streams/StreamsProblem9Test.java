package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

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
        assertEquals("shashi",res);
    }

    @Test
    public void testNthLongestString(){
        String[] arr = {"shashi","kumar","vk"};
        String res = streamsProblem9.findLongestNthString(arr,2);
        assertEquals("kumar",res);
    }


}
