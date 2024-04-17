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

    /*
    intuition is to buy the stock when
    dip compared to previous value and sell when
    greater compared to previous value
*/
    public int maxProfitA3(int[] prices) {
        int prev=prices[0],profit=0;
        for(int i=1; i<prices.length; i++){
            if(prices[i]<prev)prev=prices[i];
            else{
                profit += prices[i]-prev;
                prev=prices[i];
            }
        }
        return profit;
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