package com.github.shashi.leetcode;
import java.util.*;
public class Problem57 {
    public static void main(String[] args) {
        int[] input2 = {2,5};
        int[][] input1 = new int[][]{{1,3},{6,9}};
        Problem57 problem57 = new Problem57();
        System.out.println(problem57.insert(input1,input2));
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return insertA1(intervals, newInterval);
    }

    public int[][] insertA1(int[][] intervals, int[] newInterval){
        boolean rem = false;
        int ns = newInterval[0], ne=newInterval[1],n=intervals.length;
        List<int[]> result = new ArrayList<>();
        for(int i=0; i<n; i++){
            int[] intr = intervals[0];
            if(ns>intr[1] || rem)
                result.add(intr);
            else{
                ns = Math.min(ns, intr[0]);
                if(ne<intr[0]){
                    result.add(new int[]{ns,ne});
                    result.add(intr);
                    rem=true;
                }else if(ne<=intr[1]){
                    result.add(new int[]{ns,intr[1]});
                    rem=true;
                }

            }
        }
        int[][] res = new int[result.size()][2];
        for(int i=0; i<result.size();i++)res[i]=result.get(i);
        return res;
    }
}
