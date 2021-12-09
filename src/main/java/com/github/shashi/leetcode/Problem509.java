package com.github.shashi.leetcode;

import java.util.*;

public class Problem509 {
    public static void main(String[] args) {
        Problem509 problem119 = new Problem509();
        System.out.println(problem119.fib(4));
    }

    public int fib(int n) {
        return fibRec(n);
    }

    public int fibRec(int n){
        Map<Integer,Integer> memo = new HashMap<>();
        return fibRecFn(n,memo);
    }

    public int fibRecFn(int n, Map<Integer,Integer> memo){
        if(memo.containsKey(n)) return memo.get(n);
        int result = 0;
        if(n<2)result =n;
        else result = fibRecFn(n-1,memo)+fibRecFn(n-2,memo);
        memo.put(n,result);
        return result;
    }
}
