package com.github.shashi.leetcode;
import java.util.*;
public class Problem452{
    /*
    Minimum Number of Arrows to Burst Balloons
    There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are
     represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose
     horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

    Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
    A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the
    number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

    Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

    Example 1:
    Input: points = [[10,16],[2,8],[1,6],[7,12]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
    - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
    - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
    Example 2:
    Input: points = [[1,2],[3,4],[5,6],[7,8]]
    Output: 4
    Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
    Example 3:
    Input: points = [[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
    - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
    - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

    Constraints:
    1 <= points.length <= 105
    points[i].length == 2
    -231 <= xstart < xend <= 231 - 1

    Approach 1:
    * intuition is to sort the elements based on start point and find the number of overlapping ranges, if
    not overlapping then consider as new range and continue to merge, total ranges we got will be the ans
    algo:
    * sort the points in asc based on start points remember that we should not use a[0]-b[0] it will lead to
    overflow error instead we can compare using a[0]==b[0] 0 a[0]<b[0] -1 else 1
    * consider the 1 point as last point and count as 1, iterate array from index 1
    * if cur[0] <= last[1] then they are overlapping update the last point as
    * last point start will become max(last[0],cur[0]) and end point becomes min(lasr[1],cur[1])
    * if not overlapping then make the cur point as last point increment the count
    * return count at the end
    time & space:
    * time is nlogn for sorting space is n or nlogn based on sorting algorithm
     */
    public static void main(String[] args) {
        Problem452 problem452 = new Problem452();
        int[][] input = {{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(problem452.findMinArrowShots(input));
    }
    public int findMinArrowShots(int[][] points) {
        return findMinArrowShotsA1(points);
    }

    public int findMinArrowShotsA1(int[][] points) {
        int n = points.length, count=1;
        Arrays.sort(points,(a,b)->{
            if(a[0] <b[0])
                return -1;
            else if(a[0]==b[0])return 0;
            else return 1;
        });
        int[] last = points[0];
        for(int i=1; i<n;i++){
            int[] cur = points[i];
            if(cur[0]<= last[1]){
                last[0] = Math.max(last[0],cur[0]);
                last[1] = Math.min(last[1],cur[1]);
            }else {
                last= cur;
                count++;
            }
        }
        return count;
    }
}