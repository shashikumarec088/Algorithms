package com.github.shashi.leetcode;
import java.util.*;
public class Problem198 {
    private Map<Integer,Integer> map = new HashMap<>();
    public int rob(int[] nums) {
        return robDp(nums);
    }

    public int robDp(int[] nums){
        return dp(nums,nums.length-1);
    }

    public int dp(int[] nums, int n){
        if(n==0) return nums[0];
        if(n==1) return Math.max(nums[0],nums[1]);
        if(!map.containsKey(n))
            map.put(n, Math.max(dp(nums,n-1),dp(nums,n-2)+nums[n]));
        return map.get(n);
    }
}
