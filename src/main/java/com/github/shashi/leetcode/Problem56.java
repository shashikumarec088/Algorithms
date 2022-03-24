package com.github.shashi.leetcode;
import java.util.*;
public class Problem56 {
    public int[][] merge(int[][] intervals) {
        return mergeA1(intervals);
    }

    public int[][] mergeA1(int[][] intervals){
        Arrays.sort(intervals, (a,b)->{
            if(a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });

        LinkedList<int[]> result = new LinkedList<>();
        for(int[] interval : intervals){
            if(result.isEmpty() || result.getLast()[1]<interval[0])
                result.add(interval);
            else result.getLast()[1]=Math.max(result.getLast()[1],interval[1]);
        }

        return result.toArray(new int[result.size()][]);
    }
}