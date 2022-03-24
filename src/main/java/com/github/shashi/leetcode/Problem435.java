package com.github.shashi.leetcode;
import java.util.*;
public class Problem435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        return eraseOverlapIntervalsA3(intervals);
    }

    public int eraseOverlapIntervalsA3(int[][] intervals){
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int ans=1, max=0, n=intervals.length;
        int[] dp = new int[intervals.length];
        dp[0]=1;
        for(int i=1; i<n; i++){
            max = 0;
            for(int j=i-1; j>=0; j--){
                if(intervals[j][1] <= intervals[i][0]){
                    max = Math.max(max,dp[j]);
                    break;
                }

            }
            dp[i]=Math.max(max+1,dp[i-1]);
            ans = Math.max(ans,dp[i]);
        }
        return intervals.length-ans;
    }

    public int eraseOverlapIntervalsA2(int[][] intervals){
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        return rec(-1,0, intervals);
    }

    public int rec(int prev, int cur, int[][] intervals){
        if(cur == intervals.length)return 0;
        int taken=Integer.MAX_VALUE, notTaken=0;
        if(prev == -1 || intervals[prev][1]<= intervals[cur][0])
            taken = rec(cur, cur+1, intervals);
        notTaken = rec(prev, cur+1,intervals)+1;
        return Math.min(taken, notTaken);
    }
    public int eraseOverlapIntervalsA1(int[][] intervals){
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int end = intervals[0][1], count=0;
        for(int i=1; i<intervals.length; i++){
            if(end> intervals[i][0]){
                count++;
                end = Math.min(end,intervals[i][1]);
            }else end  = intervals[i][1];
        }
        return count;
    }
}