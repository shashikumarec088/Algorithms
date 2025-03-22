package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StreamsProblem8Test {
    private StreamsProblem8 streamsProblem8;


    @Before
    public void setUp(){
        streamsProblem8 = new StreamsProblem8();
    }


    @Test
    public void testFirstRepeatChar(){
        String res = streamsProblem8.testFirstRepeatChar("shashi");
        assertEquals("s",res);
    }


}
