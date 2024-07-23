package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem70 {
    /*
    Climbing Stairs
    You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    Example 1:
    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps
    Example 2:
    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step
    Constraints:
    1 <= n <= 45

    Approach 1: BF
    * intuition is to try all the combinations from each step and sum the ways for each combination using recursion
    algo:
    * base case is when i ==n then return 1 if i > n return 0
    * return sum of rec with i+1 and i+2
    time & space:
    * it takes 2^n time since their are 2 combinations at each step and we need to try at each value of n
    and it takes n space for recursion tree

    Approach 2: recursion with memoization
    * intuition is if we observe the combinations that we generate at each step, there are many repetitions which
    can be avoided by caching the results in map
    algo:
    * init global map of type int, int call rec with i,n
    * if i in map return map.get(i)
    * if i == n then return 1 if i> n return 0
    * call rec with i+1, i+2 and store the result in map
    * return the result at the end
    time & space:
    * it takes n time since we avoid all the repetitive calls by using map and takes n space to store results in map

    Approach 3: Dynamic programing with tabulation
    * intuition is same as approach 2 but instead of using rec with map we can use array with iterations to get to
    the nth stair
    algo:
    * create int array of size n+1, make dp[1]=1 and dp[2]=2
    * iterate from i=3 to <=n and make dp[i] = dp[i-1]+fp[i-2]
    * return dp[n] at the end
    time & space:
    * it takes n space and n time

    Approach 4: Dynamic programing with memory optimization
    * intuition is same as approach 3 but if we observe the approach 3 we at any step we are dependent on
    only last 2 values so instead of array if we store the last 2 values in variables that is enough
    algo:
    * init first=1, second=2
    * iterate from i=3 to i<=n
    * compute cur = first+second, make first=second and second=cur
    * return second at the end
    time & space:
    * it takes n time and constant space
     */
    public static void main(String[] args) {
        Problem70 problem119 = new Problem70();
        System.out.println(problem119.climbStairs(4));
    }

    public int climbStairs(int n) {
        return climbStairsA4(n);
    }

    public int climbStairsA4(int n) {
        int first=1,second=2;
        if(n==1)return 1;
        for(int i=3; i<=n;i++){
            int cur = first+second;
            first = second;
            second = cur;
        }
        return second;
    }

    public int climbStairsA3(int n) {
        int[] dp = new int[n+1];
        if(n==1)return 1;
        dp[1]=1;
        dp[2]=2;
        for(int i=3; i<=n; i++)
            dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }

    public int climbStairsA2(int n) {
        return rec2(n,0);
    }

    Map<Integer,Integer> map = new HashMap<>();
    public int rec2(int n, int i){
        if(map.containsKey(i))return map.get(i);
        if(i==n)return 1;
        if(i>n)return 0;
        map.put(i,rec2(n,i+1)+rec2(n,i+2));
        return map.get(i);
    }

    public int climbStairsA1(int n) {
        return rec1(n,0);
    }

    public int rec1(int n, int i){
        if(i==n)return 1;
        if(i>n)return 0;
        return rec1(n,i+1)+rec1(n,i+2);
    }
}
