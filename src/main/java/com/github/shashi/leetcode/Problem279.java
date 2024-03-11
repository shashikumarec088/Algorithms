package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem279 {
    Set<Integer> set = new HashSet<>();
    public int numSquares(int n) {
        return numSquaresA1(n);
    }

    public int numSquaresA1(int n) {
        for(int i=1; i*i<=n; i++)
            set.add(i*i);
        return rec(n);
    }

    public int rec(int n){
        if(set.contains(n))return 1;
        int min = 1000000000;
        for(int num : set){
            if(n<num) break;
            int newMin = rec(n-num)+1;
            min = Math.min(min,newMin);
        }
        return min;
    }
}