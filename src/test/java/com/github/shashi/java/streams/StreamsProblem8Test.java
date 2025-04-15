package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

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
        String ans = Arrays.stream("shashi".split(""))
                        .collect(Collectors.groupingBy(
                                e->e,
                                LinkedHashMap::new,
                                Collectors.counting()
                        )).entrySet().stream().filter(e->e.getValue()>1)
                        .map(e->e.getKey()).findFirst().orElse("");
        assertEquals(ans,res);
    }


}
