package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem70 {
    public static void main(String[] args) {
        Problem70 problem119 = new Problem70();
        System.out.println(problem119.climbDP(4));
    }

    public int climbStairsA3(int n){
        if(n<3)return n;
        int first = 1, second = 2;
        for(int i=3; i<=n; i++){
            int cur = first+second;
            first = second;
            second = cur;
        }
        return second;
    }

    public int climbStairs(int n) {
        return climbDP(n);
    }

    public int climbDP(int n){
        if(n==1)return n;
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3; i<=n; i++)
            dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }

    public int intclimbComb(int n){
        Map<Integer,Integer> memo = new HashMap<>();
        int[] ways = new int[]{1,2};
        return comb(n,ways,memo);
    }

    public int comb(int n, int[] ways,Map<Integer,Integer> memo){
        if(memo.containsKey(n)) return memo.get(n);
        else if(n <0) return -1;
        else if(n==0) return 1;
        else{
            int total = 0;
            for(int i : ways){
                int count = comb(n-i,ways,memo);
                if(count !=-1) total+=count;
            }
            if(total == 0) total=-1;
            memo.put(n,total);
            return total;
        }
    }
}
