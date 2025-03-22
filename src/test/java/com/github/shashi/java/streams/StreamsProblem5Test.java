package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StreamsProblem5Test {
    private StreamsProblem5 streamsProblem5;


    @Before
    public void setUp(){
        streamsProblem5 = new StreamsProblem5();
    }


    @Test
    public void testNthSalaryByDepartment(){
        List<StreamsProblem5.Employee> employees = Arrays.asList(
                new StreamsProblem5.Employee("Alice", "HR", 5000),
                new StreamsProblem5.Employee("Bob", "HR", 7000),
                new StreamsProblem5.Employee("Charlie", "HR", 6000),
                new StreamsProblem5.Employee("David", "IT", 9000),
                new StreamsProblem5.Employee("Eve", "IT", 12000),
                new StreamsProblem5.Employee("Frank", "IT", 11000),
                new StreamsProblem5.Employee("Grace", "Finance", 8000),
                new StreamsProblem5.Employee("Hank", "Finance", 8500)
        );
        Map<String,Integer> res = streamsProblem5.findNthSalaryPerDepartment(employees,2);
        Map<String,Integer> expected = Map.of("HR",6000,"IT",11000,"Finance",8000);
        assertEquals(expected,res);
    }


}
