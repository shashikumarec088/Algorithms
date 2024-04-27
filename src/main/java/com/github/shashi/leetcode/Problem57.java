package com.github.shashi.leetcode;
import java.util.*;
public class Problem57 {
    /*
    Insert Interval
    You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start
    and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval
    newInterval = [start, end] that represents the start and end of another interval.
    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
    intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
    Return intervals after the insertion.
    Note that you don't need to modify intervals in-place. You can make a new array and return it.

    Example 1:
    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Example 2:
    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    Output: [[1,2],[3,10],[12,16]]
    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
    Constraints:

    0 <= intervals.length <= 104
    intervals[i].length == 2
    0 <= starti <= endi <= 105
    intervals is sorted by starti in ascending order.
    newInterval.length == 2
    0 <= start <= end <= 105

    Approach 1:
    intuition is to check if interval is before or after any interval if so add as new interval else
    merge it with overlapping interval
    algo:
    * create a empty list to capture the updated intervals, flag to check if new interval is merged or not.
    * iterate over all intervals check if new interval is before current interval and it is still not merged,
    if so add it to the list.
    * if new interval overlaps with current interval and still not merged then merge with current interval and update
    both the start and end bounds ex new interval 0,3 and first interval is 1,2 in this case both the bounds should be
    updated.
    * if the list is empty then add the current interval else take the last interval from the list and check if current
    interval overlaps with the last interval, if so merge it else add the current interval.
    * at the end if the new interval is still not merged then add to the list
    * return the list as an array at the end
     */
    public static void main(String[] args) {
        int[] input2 = {2,5};
        int[][] input1 = new int[][]{{1,3},{6,9}};
        Problem57 problem57 = new Problem57();
        System.out.println(problem57.insert(input1,input2));
    }
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return insertA1(intervals, newInterval);
    }

    public int[][] insertA1(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        boolean merged = false;
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(!merged && newInterval[1]<intervals[i][0]){
                list.add(newInterval);
                merged=true;
            }
            else if(!merged && intervals[i][1]>=newInterval[0]){
                intervals[i][1] = Math.max(intervals[i][1],newInterval[1]);
                intervals[i][0] = Math.min(intervals[i][0],newInterval[0]);
                merged=true;
            }
            if(list.size()==0)
                list.add(intervals[i]);
            else{
                int[] last = list.get(list.size()-1);
                if(last[1]>=intervals[i][0])
                    last[1] = Math.max(intervals[i][1],last[1]);
                else list.add(intervals[i]);
            }
        }
        if(!merged)list.add(newInterval);
        return list.toArray(new int[list.size()][]);
    }
}
