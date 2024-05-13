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
    intuition is to add all the intervals before new interval to list, then merge all the overlapping
    intervals with new interval and then add new interval to list and then add the remaining intervals to list
    algo:
    * create a empty list to capture the updated intervals
    * iterate from i=0 and check for each interval if before new interval ie intervals[i][1] < new[0] then add to list
    * iterate over the remaining interval until the new[1] >= intervals[i][0] keep updating the
    new[0] = min(new[0],intervals[i][0]) new[1] = max(new[1],intervals[i][1])
    * after merging add the merged new interval to list;
    * add the remaining intervals to list and return the list at the end
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
        List<int[]> list = new ArrayList<>();
        int n = intervals.length,i=0;
        // add intervals before new interval
        while(i<n && intervals[i][1] < newInterval[0])
            list.add(intervals[i++]);
        // merge overlapping intervals with new interval
        while(i<n && newInterval[1] >=intervals[i][0]){
            newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            i++;
        }
        // add merged new interval
        list.add(newInterval);
        // add non overlapping intervals to list
        while(i<n)
            list.add(intervals[i++]);
        return list.toArray(new int[list.size()][]);
    }
}
