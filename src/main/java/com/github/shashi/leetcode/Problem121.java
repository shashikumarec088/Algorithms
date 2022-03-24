package com.github.shashi.leetcode;

public class Problem121 {
    public int maxProfit(int[] prices) {
        return maxProfitA3(prices);
    }

    public int maxProfitA3(int[] nums){
        int curSum=0, maxSum=0,n=nums.length;
        for(int i=1; i<n; i++){
            curSum+= nums[i]-nums[i-1];
            if(curSum<0)curSum=0;
            maxSum=Math.max(maxSum,curSum);
        }
        return maxSum;
    }

    public int maxProfitA2(int[] nums){
        int n=nums.length, ans=0, min=nums[0];
        for(int i=1; i<n; i++){
            ans = Math.max(ans, nums[i]-min);
            min = Math.min(min,nums[i]);
        }
        return ans;
    }

    public int maxProfitA1(int[] nums){
        int ans=0, n=nums.length;
        for(int i=0; i<n; i++)
            for(int j=i+1; j<n; j++)
                ans = Math.max(ans, nums[j]-nums[i]);
        return ans;
    }
}