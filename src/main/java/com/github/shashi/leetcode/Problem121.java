package com.github.shashi.leetcode;

public class Problem121 {
    /*
    121. Best Time to Buy and Sell Stock
    You are given an array prices where prices[i] is the price of a given stock on the ith day.
    You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the
    future to sell that stock.
    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

    Example 1:
    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
    Example 2:
    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transactions are done and the max profit = 0.

    Constraints:
    1 <= prices.length <= 105
    0 <= prices[i] <= 104

    Approach 1: bf iterative
    * intuition is to find the profits among all the possible transactions and consider hte maximum among
    those
    algo:
    * init profit=0, n as number of prices
    * iterate i=0 to <n-1 and j=i+1 to j<n
    * update profit with max of profit and prices[j]-prices[i];
    time & space:
    * it takes n^2 time and const space

    Approach 2: One pass solution
    * intuition is that for every element we calculate the difference with the min element we seen so far
    and update profit if it is greater than existing profit
    algo:
    * intit min=prices[0] and profit=0, n as number of prices
    * iterate from i=1 to n
    * make min as min of min, prices[i]
    * profit as max of profit and (prices[i]-min)
    time & space:
    * it takes n time and constant space
     */
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