package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StreamsProblem2Test {
    private StreamsProblem2 streamsProblem2;

    @Before
    public void setUp(){
        streamsProblem2 = new StreamsProblem2();
    }


    @Test
    public void ThreeDupChars(){
        String input = "shashi";
        Map<String,Long> res = streamsProblem2.findDuplicateCharacterAndOccurance(input);
        Map<String,Long> expected = Map.of("s",2l,"h",2l,"a",1l,"i",1l);
        assertEquals(res,expected);
    }
}
