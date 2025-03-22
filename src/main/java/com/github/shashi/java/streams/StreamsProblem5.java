package com.github.shashi.java.streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsProblem5 {
    /*
    Find Nth salary in for each department
     */

    public record Employee(String name, String department, Integer salary){}

    public Map<String,Integer> findNthSalaryPerDepartment(List<Employee> employees, int n){
                return employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        e->e.department,
                                        Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                list -> list.stream()
                                                        .map(Employee::salary)
                                                        .distinct()
                                                        .sorted(Comparator.reverseOrder())
                                                        .skip(n-1)
                                                        .findFirst().orElse(0)
                                        )));
    }


}
