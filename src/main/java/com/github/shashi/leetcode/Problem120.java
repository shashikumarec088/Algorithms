package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem120 {
    /*
    Triangle
    Given a triangle array, return the minimum path sum from top to bottom.
    For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on
    the current row, you may move to either index i or index i + 1 on the next row.

    Example 1:
    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    Output: 11
    Explanation: The triangle looks like:
       2
      3 4
     6 5 7
    4 1 8 3
    The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
    Example 2:
    Input: triangle = [[-10]]
    Output: -10
    Constraints:
    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -104 <= triangle[i][j] <= 104
    Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

    Approach 1: bf recursion TLE
    * intuition is to find the sum of all the paths in the triangle and return the min sum among those. this can
    be done by recursion, where we start from first level and first element and return the ans as sum of the
    current element and the min of rec of next level cur position element and rec of next level next position
    element
    * that is triangle[level][pos] + min(rec(level+1,pos), rec(level+1,pos+1)) and the base case is when level
    is equal to list.length we return 0.
    algo:
    * call rec with triangle,level=0, pos=0
    * in recursion base case is when level==triangle.length() then we reached last level no elements down to it
    return 0
    * call rec with updated values of level and position, m1 = rec(triangle,level+1,pos) and m2 = rec(triangle,
    level+1,pos+1)
    * return triangle.get(level).get(pos) + min(m1,m2)
    time & space:
    * it takes 2^n time as there are 2 recursions per position and n space for recursion stack.

    Approach 2: recursion with memoization
    * in the approach 1 it is leading to TLE, if we observe the solution there are many repeated calculations
    that we are doing this can be avoided by storing the calculations in an array and reusing the results.
    * we can have Integer array of 2 dimentions 1 for level and 1 for position at each recursive call if  the
    value at level,pos is not null then we can return that else we compute the value and store in array and
    then return.
    algo:
    * create the global array of type Integer of size triangle size and width is triangle last list size
    * call rec with triangle, 0, 0
    * if the level is triangle size then return 0
    * if cache[level][pos] is not null then return from cache
    * cache[level][pos] = triangle.get(level).get(pos)+min(rec(triangle,level+1,pos),rec(triangle,level+1,pos+1)
    * return cache[level][pos]
    time & space:
    * it takes n^2 time as duplicate calls are served from cache and space complexity is n^2 since we use 2D array
    of size n and each level has size n

    Approach 3: bottom up using array
    * intuition is in bottom up approach we can use the dp array of size n and reuse the same array for all the
    levels and at the end the result will be at position 0.
    algo:
    * init dp list of size n and copy all the elements in last level of triangle to dp
    * iterate i=n-2 to i>=0 i dec, and iterate j=0 to j<=i and inc j
    * update dp of j = triangle[i][j]+ min(dp[j],dp[j+1])
    * return dp[0] at the end
    time & space:
    * it takes n^2 time and n space for dp array.

    Approach 4: bottom up dp with using input triangle last row as dp
    * intuition is same as approach 3 instead of creating the new list we can use the same input list at
    last level to store the results it reduce the space complexity to const but will modify the input.

     */

    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotalA4(triangle);
    }


    public int minimumTotalA4(List<List<Integer>> list) {
        int n = list.size();
        List<Integer> dp =list.get(n-1);
        for(int i=n-2;i>=0;i--){
            for(int j=0; j<=i;j++){
                dp.set(j,list.get(i).get(j)+Math.min(dp.get(j),dp.get(j+1)));
            }
        }
        return dp.get(0);
    }

    public int minimumTotalA3(List<List<Integer>> list) {
        int n = list.size();
        List<Integer> dp =new ArrayList<Integer>();
        for(int num:list.get(n-1))dp.add(num);
        for(int i=n-2;i>=0;i--){
            for(int j=0; j<=i;j++){
                dp.set(j,list.get(i).get(j)+Math.min(dp.get(j),dp.get(j+1)));
            }
        }
        return dp.get(0);
    }

    public int minimumTotalA2(List<List<Integer>> list) {
        cache = new Integer[list.size()][list.get(list.size()-1).size()];
        return rec2(list,0,0);
    }
    Integer[][] cache;
    public int rec2(List<List<Integer>> list, int level, int pos){
        if(level==list.size())return 0;
        if(cache[level][pos]!=null)return cache[level][pos];
        cache[level][pos]= list.get(level).get(pos)+
                Math.min(rec2(list,level+1,pos),
                        rec2(list,level+1,pos+1));
        return cache[level][pos];
    }

    public int minimumTotalA1(List<List<Integer>> list) {
        return rec(list,0,0);
    }

    public int rec(List<List<Integer>> list, int level, int pos){
        if(level==list.size())return 0;
        return list.get(level).get(pos)+
                Math.min(rec(list,level+1,pos),
                        rec(list,level+1,pos+1));
    }
}
