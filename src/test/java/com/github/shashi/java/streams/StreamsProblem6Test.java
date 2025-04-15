package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String ans2 = Arrays.stream("shashi".split(""))
                        .collect(Collectors.groupingBy(
                                e->e,
                                LinkedHashMap::new,
                                Collectors.counting()
                        )).entrySet()
                        .stream().filter(e->e.getValue()==1).map(e->e.getKey())
                        .findFirst().orElse("");
        assertEquals(ans2,res);
    }


}
