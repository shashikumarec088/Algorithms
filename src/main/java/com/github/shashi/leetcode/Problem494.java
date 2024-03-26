package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem494 {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWaysA1(nums, target);
    }

    int count=0;

    public int findTargetSumWaysA3(int[] nums, int target) {
        int total = Arrays.stream(nums).sum(),  n = nums.length;
        int[][] dp = new int[n][2*total+1];
        dp[0][total+nums[0]] += 1;
        dp[0][total-nums[0]] += 1;
        for(int i=1; i<n; i++){
            for(int sum= -total; sum<= total; sum++){
                if(dp[i-1][total+sum]>0){
                    dp[i][total+sum+nums[i]] += dp[i-1][total+sum];
                    dp[i][total+sum-nums[i]] += dp[i-1][total+sum];
                }
            }
        }
        return Math.abs(target)> total?0: dp[n-1][total+target];
    }

    public int findTargetSumWaysA1(int[] nums, int target) {
        return findTargetSumWaysA2(nums,target);
    }

    public int findTargetSumWaysA2(int[] nums, int target) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int[][] memo = new int[n][2*sum+1];
        for(int[] arr : memo)
            Arrays.fill(arr,Integer.MIN_VALUE);
        return rec2(nums,target,0,0,memo,sum);
    }

    public int rec2(int[] nums, int target, int index,
                    int sum, int[][] memo, int total){
        if(index == nums.length){
            if(sum==target)return 1;
            else return 0;
        }

        if(memo[index][sum+total] != Integer.MIN_VALUE)
            return memo[index][sum+total];

        int add = rec2(nums, target, index+1, sum+nums[index],
                memo, total);
        int sub = rec2(nums, target, index+1, sum-nums[index],
                memo, total);
        memo[index][sum+total] = add+sub;
        return memo[index][sum+total];
    }


    public void rec(int[] nums, int index, int sum, int target){
        if(index==nums.length){
            if(sum==target)count++;
            return;
        }
        for(int i=0; i<2; i++){
            if(i==0)rec(nums,index+1,sum+nums[index],target);
            else rec(nums,index+1,sum-nums[index],target);
        }
    }

}