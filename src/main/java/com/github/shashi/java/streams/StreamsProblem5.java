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

    public record Employee2(String name, String department, String subDepartment, Integer salary){}

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

    public Map<String,Map<String,Integer>> findNthSalaryPerSubDepartment(List<Employee2> employees, int n){
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.department,
                                Collectors.groupingBy(
                                        e->e.subDepartment,
                                        Collectors.collectingAndThen(
                                                Collectors.mapping(e->e.salary,Collectors.toList()),
                                                salaries->salaries.stream()
                                                        .sorted(Collections.reverseOrder())
                                                        .skip(n-1)
                                                        .findFirst().orElse(0)
                                        )
                                )
                        )
                );
    }

    public Map<String,Map<String,Double>> findAvgSalaryPerSubDepartment(List<Employee2> employees){
        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> e.department,
                                Collectors.groupingBy(
                                        e->e.subDepartment,
                                        Collectors.averagingInt(e->e.salary)
                                )
                        )
                );
    }


}
