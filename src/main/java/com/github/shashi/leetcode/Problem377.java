package com.github.shashi.leetcode;
import java.util.*;
public class Problem377 {
    public int combinationSum4(int[] nums, int target) {
        return combinationSum4A2(nums, target);
    }

    public int combinationSum4A1(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        return rec(nums,target,map);
    }

    public int combinationSum4A2(int[] nums, int target){
        int[] dp = new int[target+1];
        dp[0]=1;
        for(int i=0; i<target+1; i++){
            for(int num : nums)
                if(i-num>=0)
                    dp[i] += dp[i-num];
        }
        return dp[target];
    }

    public int rec(int[] nums, int tar,Map<Integer,Integer> cache){
        if(tar==0)return 1;
        if(cache.containsKey(tar))return cache.get(tar);
        int res=0;
        for(int num:nums){
            if(tar-num>=0)
                res += rec(nums, tar-num,cache);
        }
        cache.put(tar,res);
        return res;
    }
}