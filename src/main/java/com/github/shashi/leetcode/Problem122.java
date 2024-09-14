package com.github.shashi.leetcode;

public class Problem122 {
    /*
    122. Best Time to Buy and Sell Stock II
    You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
    On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at
    any time. However, you can buy it then immediately sell it on the same day.
    Find and return the maximum profit you can achieve.

    Example 1:
    Input: prices = [7,1,5,3,6,4]
    Output: 7
    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
    Total profit is 4 + 3 = 7.
    Example 2:
    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Total profit is 4.
    Example 3:
    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum
    profit of 0.
    Constraints:
    1 <= prices.length <= 3 * 104
    0 <= prices[i] <= 104

    Approach 1: brute force recursion
    * intuition is to try all the buy and sell combinations which lead to profit and consider the maximum
    profit among them
    algo:
    * call recursion with prices and index 0
    * the base case is when index is >= n then return 0
    * init max=0
    * iterate i=index to i<n
    * init maxProfit=0 and iterate j=i+1 to j<n
    * check if price at i < price at j if so
    * profit = prices[j]-prices[i] + rec(prices,j+1)
    * if profit is > maxProfit then update the maxProfit
    * after j loop check if maxProfit > max if so update max
    * return max at the end.
    time & space:
    * it takes n^n time ans n space for recursion stack

    Approach 2: one pass solution
    * intuition is to buy the stock when it dips and sell whenever it is higher and consider all the profits
    algo:
    * init prev=prices[0] and profit=0
    * iterate i=1 to i<n
    * if prices[i] < prev then update prev with prices[i]
    * else make profit+= prices[i]-prev
    * and make prev=prices[i]
    * return profit at the end
    time & space:
    * it takes const space and n time.

    Approach 3: recursion with choices
    * intuition is that at any point either we can purchase or sell or move on to next day, to get the
    maximum profit we need to consider moving on to next day and also either selling or buying the stock
    based on either we are holding the stock or not, if we are already holding then we need to sell the stock
    else we need to purchase
    algo:
    * call rec with prices, pos=0, holding=0
    * in recursion base case to check if pos>=prices.length if so then we return 0
    * we make 2 choices either skip or take action, when skipping
    * cal rec with i+1, holding and store it in onAction
    * when taking action if holding is 0 then call rec with i+1,1 and subtract prices[i] from it
    this is because we are buying the stock
    * if holding is 1 then call rec with i+1,0 and add prices[i] to it as we are selling the stock
    here we are passing 0 as holding because once we sell the stock we will not be holding anything
    so it indicates that in rec we need ti purchase the stock
    * return max of noAction and action
    time & space:
    * time 2^n as their will be 2 recursion call at each step and n space

    Approach 4: recursion with choices and memoization
    * intuition is same as approach 3 but we use 2d array of size n,2 of type Integers memo to avoid
    repeated calculations
    algo:
    * init global variable memo of size n,2 of type Integer  and call rec with prices,0,0
    * base case is same as approach 3
    * check if memo[i][holding] is not null if so then return from memo
    * else compute the max of action and noAction same as in approach 3 and store in memo[i][holding]
    * and return from it.
    time & space:
    * it takes n^2 time as we make 2 recursive calls at each step and avoid repetition and takes n space

    Approach 5: bottom up dynamic programming
    * intuition is same as approach 4 but we build the solution from start, initially we will have array
    of size n,2. since we do not hold anything 0,0 will be 0 and 0,1 will be -prices[0]
    * then at any position the max profit we make when we hold or not hold the stock will be
    when we do not hold then dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i])
    * dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])
    * then the max profit we make will be max of these two values when i=n-1
    algo:
    * init dp of size n,2 of type int, make dp[0][0]=0 and dp[0][1]= -prices[0]
    * iterate from i=1 to <n
    * make dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i])
    * make dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])
    * return max of dp[n-1][0] and dp[n-1][1]
     */
    public int maxProfit(int[] prices) {
        return maxProfitA2(prices);
    }

    public int maxProfitA5(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-nums[0];
        for(int i=1;i<n;i++){
            dp[i][0]= Math.max(dp[i-1][1]+nums[i],dp[i-1][0]);
            dp[i][1]= Math.max(dp[i-1][1],dp[i-1][0]-nums[i]);
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);
    }

    Integer[][] memo;
    public int maxProfitA4(int[] prices) {
        memo = new Integer[prices.length][2];
        return rec4(prices,0,0);
    }

    public int rec4(int[] prices,int pos, int holding){
        if(pos>=prices.length)return 0;
        if(memo[pos][holding]!=null)return memo[pos][holding];
        int noAction = rec4(prices,pos+1,holding);
        int action = holding==0?(-prices[pos]+rec4(prices,pos+1,1)):
                (prices[pos]+rec4(prices,pos+1,0));
        memo[pos][holding]= Math.max(noAction,action);
        return memo[pos][holding];
    }

    public int maxProfitA3(int[] prices) {
        return rec3(prices,0,0);
    }

    public int rec3(int[] prices,int pos, int holding){
        if(pos>=prices.length)return 0;
        int noAction = rec3(prices,pos+1,holding);
        int action = holding==0?(-prices[pos]+rec3(prices,pos+1,1)):
                (prices[pos]+rec3(prices,pos+1,0));
        return Math.max(noAction,action);
    }

    public int maxProfitA2(int[] prices) {
        int n=prices.length,prev=prices[0],profit=0;
        for(int i=1; i<n;i++){
            if(prices[i]<prev)prev=prices[i];
            else{
                profit+=prices[i]-prev;
                prev=prices[i];
            }
        }
        return profit;
    }

    public int maxProfitA1(int[] prices) {
        return rec(prices,0);
    }

    public int rec(int[] prices, int pos){
        if(pos>=prices.length)return 0;
        int max=0;
        for(int i=pos;i<prices.length;i++){
            int maxProfit=0;
            for(int j=i+1;j<prices.length;j++){
                if(prices[i]<prices[j]){
                    int profit=prices[j]-prices[i]+rec(prices,j+1);
                    if(profit>maxProfit)
                        maxProfit=profit;
                }
            }
            if(maxProfit>max)max=maxProfit;
        }
        return max;
    }
}