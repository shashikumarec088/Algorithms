package com.github.shashi.leetcode;

public class Problem1749 {
    public int maxAbsoluteSum(int[] nums) {
        return maxAbsoluteSumA1(nums);
    }

    public int maxAbsoluteSumA1(int[] nums){
        int ans=nums[0], cur = nums[0], minCur=nums[0], n = nums.length;
        for(int i=1; i<n; i++){
            cur = Math.max(cur+nums[i],nums[i]);
            minCur = Math.min(minCur+nums[i],nums[i]);
            int temp = Math.max(Math.abs(cur),Math.abs(minCur));
            ans = Math.max(Math.abs(temp),Math.abs(ans));
        }
        return Math.abs(ans);
    }
}