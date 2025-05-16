package com.github.shashi.leetcode;
import java.util.*;
public class Problem435 {
    /*
    Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
    you need to remove to make the rest of the intervals non-overlapping.

    Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are
    non-overlapping.

    Example 1:

    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
    Example 2:

    Input: intervals = [[1,2],[1,2],[1,2]]
    Output: 2
    Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
    Example 3:

    Input: intervals = [[1,2],[2,3]]
    Output: 0
    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

    approach 1: sorting and counting
    * intuition is to sort the intervals on start
    and count when end overlaps with next and update end
    to min of both
    algo:
    * sort intervals on start time
    * init prev=0,count=0,cur=1,n = len(intervals)
    * iterate i=1 to n
        * if cur[0] >= prev
            prev = cur[1]
        else
            prev = min(prev,cur[1])
            count ++
    return count

    time & space:
    * it takes n log n time and n space

     */
    public int eraseOverlapIntervals(int[][] intervals) {
        return eraseOverlapIntervalsA1(intervals);
    }


    public int eraseOverlapIntervalsA1(int[][] intervals) {
        int prev=Integer.MIN_VALUE, count=0;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        for(int i=0; i<intervals.length;i++){
            if(intervals[i][0]>=prev){
                prev = intervals[i][1];
            }else{
                count++;
                prev = Math.min(prev,intervals[i][1]);
            }
        }
        return count;
    }
}