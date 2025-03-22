package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StreamsProblem4Test {
    private StreamsProblem4 streamsProblem4;

    @Before
    public void setUp(){
        streamsProblem4 = new StreamsProblem4();
    }


    @Test
    public void testNthSalary(){
        Map<String,Integer> map = Map.of("shashi",100,
                "ajay",150,"sanjay",230, "vijay",100,"charan",80);
        int res = streamsProblem4.findNthSalary(map,2);
        assertEquals(res,150);
    }

    @Test
    public void testNthSalaryNames(){
        Map<String,Integer> map = Map.of("shashi",100,
                "ajay",150,"sanjay",230, "vijay",100,"charan",80);
        List<String> res = streamsProblem4.findNthSalaryNames(map,3);
        assertEquals( Arrays.asList("shashi","vijay"),res);
    }
}
