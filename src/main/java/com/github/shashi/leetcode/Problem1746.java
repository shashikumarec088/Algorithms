package com.github.shashi.leetcode;

public class Problem1746 {
    public int maxSumAfterOperation(int[] nums) {
        return maxSumAfterOperationA1(nums);
    }

    public int maxSumAfterOperationA1(int[] nums){
        int nsq = nums[0], sq=nums[0]*nums[0],n=nums.length;
        int ans = Math.max(nsq,sq);
        for(int i=1; i<n; i++){
            sq = Math.max(sq+nums[i],nsq+nums[i]*nums[i]);
            // need to understand this part
            nsq = Math.max(nsq+nums[i],0);
            ans = Math.max(ans,sq);
        }
        return ans;

    }
}