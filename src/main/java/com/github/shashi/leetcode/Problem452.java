package com.github.shashi.leetcode;
import java.util.*;
public class Problem452{
    public static void main(String[] args) {
        Problem452 problem452 = new Problem452();
        int[][] input = {{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(problem452.findMinArrowShots(input));
    }
    public int findMinArrowShots(int[][] points) {
        return findMinArraowShotsA1(points);
    }

    public int findMinArraowShotsA1(int[][] intervals){
        int n = intervals.length;
        Arrays.sort(intervals,(a,b)->{
            if(a[0]!=b[0])return ((long)a[0]-b[0])<0?-1:1;
            else return ((long)a[1]-b[1])<0?-1:1;});
        int arrows = 1, s=intervals[0][0], e=intervals[0][1];
        for(int i=1; i<n; i++){
            int[] pt = intervals[i];
            if(pt[0]<=e){
                s= Math.max(s,pt[0]);
                e = Math.min(e,pt[1]);
            }else{
                arrows++;
                s=pt[0];
                e=pt[1];
            }
        }
        return arrows;
    }
}