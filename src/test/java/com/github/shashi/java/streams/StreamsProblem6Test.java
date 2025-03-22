package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StreamsProblem6Test {
    private StreamsProblem6 streamsProblem6;


    @Before
    public void setUp(){
        streamsProblem6 = new StreamsProblem6();
    }


    @Test
    public void testFirstNonRepeatChar(){
        String res = streamsProblem6.firstNonRepeatCharacter("shashi");
        assertEquals("a",res);
    }


}
