package com.github.shashi.java.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void testNthSalaryBySebDepartment(){
        List<StreamsProblem5.Employee2> employees = Arrays.asList(
                new StreamsProblem5.Employee2("Alice", "HR","TA", 5000),
                new StreamsProblem5.Employee2("Bob", "HR","TA", 7000),
                new StreamsProblem5.Employee2("Alice", "HR","TM", 5000),
                new StreamsProblem5.Employee2("Bob", "HR","TM", 7000),
                new StreamsProblem5.Employee2("David", "IT","SUP", 9000),
                new StreamsProblem5.Employee2("Eve", "IT","SUP", 12000),
                new StreamsProblem5.Employee2("David", "IT","SEC", 9000),
                new StreamsProblem5.Employee2("Eve", "IT","SEC", 12000),
                new StreamsProblem5.Employee2("Grace", "Finance","TAX", 8000),
                new StreamsProblem5.Employee2("Hank", "Finance","TAX", 8500),
                new StreamsProblem5.Employee2("Grace", "Finance","BEN", 8000),
                new StreamsProblem5.Employee2("Hank", "Finance","BEN", 8500)
        );
        Map<String,Map<String,Integer>> res = streamsProblem5.findNthSalaryPerSubDepartment(employees,2);
        Map<String,Map<String,Integer>> expected = Map.of("HR",Map.of("TA",5000,"TM",5000),
                "IT",Map.of("SUP",9000,"SEC",9000),
                "Finance",Map.of("TAX",8000,"BEN",8000));
        assertEquals(expected,res);
    }

    @Test
    public void testAvgthSalaryBySebDepartment(){
        List<StreamsProblem5.Employee2> employees = Arrays.asList(
                new StreamsProblem5.Employee2("Alice", "HR","TA", 5000),
                new StreamsProblem5.Employee2("Bob", "HR","TA", 7000),
                new StreamsProblem5.Employee2("Alice", "HR","TM", 5000),
                new StreamsProblem5.Employee2("Bob", "HR","TM", 7000),
                new StreamsProblem5.Employee2("David", "IT","SUP", 9000),
                new StreamsProblem5.Employee2("Eve", "IT","SUP", 12000),
                new StreamsProblem5.Employee2("David", "IT","SEC", 9000),
                new StreamsProblem5.Employee2("Eve", "IT","SEC", 12000),
                new StreamsProblem5.Employee2("Grace", "Finance","TAX", 8000),
                new StreamsProblem5.Employee2("Hank", "Finance","TAX", 8500),
                new StreamsProblem5.Employee2("Grace", "Finance","BEN", 8000),
                new StreamsProblem5.Employee2("Hank", "Finance","BEN", 8500)
        );
        Map<String,Map<String,Double>> res = streamsProblem5.findAvgSalaryPerSubDepartment(employees);
        Map<String,Map<String,Double>> expected = Map.of("HR",Map.of("TA",6000.0,"TM",6000.0),
                "IT",Map.of("SUP",10500.0,"SEC",10500.0),
                "Finance",Map.of("TAX",8250.0,"BEN",8250.0));
        assertEquals(expected,res);
    }


}
