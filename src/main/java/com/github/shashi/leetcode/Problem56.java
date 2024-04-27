package com.github.shashi.leetcode;
import java.util.*;
public class Problem56 {
    /*
    Merge Intervals
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and
    return an array of the non-overlapping intervals that cover all the intervals in the input.

    Example 1:
    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
    Example 2:
    Input: intervals = [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.

    Constraints:
    1 <= intervals.length <= 104
    intervals[i].length == 2
    0 <= starti <= endi <= 104

    Approach 1:
    * intuition is to sort the intervals and keep merging the overlapping intervals if not then add new interval
    algo:
    * sort the input array based on custom comparator Arrays.sort(input,(a,b)->a[0]-b[0])
    * create arrayList of arrays to hold the ans, add first element to array.
    * iterate from i=1 to n, for reach cur check if prev end is >= cur start if so then update the prev end with
    max of prev end and cur end
    * else add the cur interval to list
    * at the end convert the list array using list.toArray(new int[list.size()][])
    time & space:
    time is nlogn and space is n min for sorting and result
     */
    public int[][] merge(int[][] intervals) {
        return mergeA1(intervals);
    }

    public int[][] mergeA1(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int n = intervals.length;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        list.add(intervals[0]);
        for(int i=1; i<n; i++){
            int[] prev = list.get(list.size()-1);
            if(prev[1]>= intervals[i][0])
                prev[1] = Math.max(prev[1],intervals[i][1]);
            else list.add(intervals[i]);
        }
        return list.toArray(new int[list.size()][]);
    }
}