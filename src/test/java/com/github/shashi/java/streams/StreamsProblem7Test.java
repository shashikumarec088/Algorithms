package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StreamsProblem7Test {
    private StreamsProblem7 streamsProblem7;

    @Before
    public void setUp(){
        streamsProblem7 = new StreamsProblem7();
    }


    @Test
    public void ThreeDupChars(){
        int[] input = {1,2,2,3,4,2,3,4};
        int res = streamsProblem7.findNthHighest(input,2);
        int ans = Arrays.stream(input).boxed()
                        .distinct()
                                .sorted(Comparator.reverseOrder())
                .skip(1).findFirst().orElse(-1);
        assertEquals(res,ans);
    }
}
