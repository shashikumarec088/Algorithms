package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StreamsProblem1Test {
    private StreamsProblem1 streamsProblem1;

    @Before
    public void setUp(){
        streamsProblem1 = new StreamsProblem1();
    }


    @Test
    public void ThreeDupChars(){
        String input = "shashi kumar";
        List<String> res = streamsProblem1.findDuplicateCharacter(input);
        int ans1 = 6 % 5;

        List<String> expected = Arrays.asList("a","s","h");
        assertEquals(res,expected);
    }
}
