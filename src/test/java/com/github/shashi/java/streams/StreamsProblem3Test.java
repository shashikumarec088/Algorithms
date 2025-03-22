package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StreamsProblem3Test {
    private StreamsProblem3 streamsProblem3;

    @Before
    public void setUp(){
        streamsProblem3 = new StreamsProblem3();
    }


    @Test
    public void test(){
        Integer[] input = {0,1,1,0};
        Integer[] res = streamsProblem3.seperateOnes(input);
        Map<String,Long> expected = Map.of("s",2l,"h",2l,"a",1l,"i",1l);
        assertEquals(res,expected);
    }
}
