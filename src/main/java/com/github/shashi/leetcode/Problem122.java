package com.github.shashi.leetcode;

public class Problem122 {
    public int maxProfit(int[] prices) {
        return maxProfitA2(prices);
    }

    public int maxProfitA2(int[] nums){
        int peak = Integer.MAX_VALUE;
        int valley = peak, sum=0;
        for(int num : nums){
            if(num < peak){
                sum+= peak-valley;
                peak = num;
                valley=peak;
            }else peak=num;
        }
        // handling prices without vally
        sum+= peak-valley;
        return sum;
    }

    public int maxProfitA1(int[] nums){
        int min=nums[0], ans=0,n=nums.length;
        for(int i=1; i<n; i++){
            if(min<nums[i])
                ans+=nums[i]-min;
            min=nums[i];
        }
        return ans;
    }
}