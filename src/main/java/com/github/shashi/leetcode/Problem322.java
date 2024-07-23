package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem322 {
    /*
        Coin Change
    You are given an integer array coins representing coins of different denominations and an integer amount
    representing a total amount of money.
    Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made
    up by any combination of the coins, return -1.
    You may assume that you have an infinite number of each kind of coin.

    Example 1:
    Input: coins = [1,2,5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1
    Example 2:
    Input: coins = [2], amount = 3
    Output: -1
    Example 3:
    Input: coins = [1], amount = 0
    Output: 0

    Constraints:
    1 <= coins.length <= 12
    1 <= coins[i] <= 231 - 1
    0 <= amount <= 104

    Approach 1: brute force recursion
    * intuition is to try all the coins, for each coin subtract it from amount and call rec with updated value of amount
    when the amount reaches 0 that means we are able to get the change so we return 0 indicating combination is valid
    if the amount becomes -ve then we return -1 indicating cont get change.
    * for each coin we need to perform the recursion and update the min coins and return the mincoins at the end
    algo:
    * call rec with coins, amount if amount is 0 return 0 if amount < 0 return -1
    * init count = Integer.MAX_VALUE,
    * iterate over all the coins, for each coin call rec with amount-coin and store res
    * if res !=-1 then update count with min of count and res+1
    * if count is not Integer.MAX_VALUE, then return count else -1 a answer.
    time & space:
    * if s is the amount then recursion stack takes s amount in the worst case, for each coin subtracted their
    will be n coins to try so time complexity will be s^n

    Approach 2: recursion with memoization
    * in the above approach we do multiple repeated computations which can be avoided by using map
    algo:
    * create the global map of type int,int call rec with coins, amount
    * if amount ==0 return 0, if amount < 0 return -1;
    * if map contains amount return value present in map
    * init count= Integer.MAX_VALUE
    * iterate over the coins and call rec, if res is not -1 then update the count with min of count,res+1
    * put value at amount in map with if count== Integer.MIN_VALUE then -1 else count
    * return value in map

    time & space:
    * it takes s space and s * n time where n is number of coins

    Approach 3: bottom up dp
    * intuition is to use the dp array of size amount+1, fill with max value, we will use amount+1 to avoid the
    overflow issue. then we build the solution from bottom, by init dp[0]=0, and using the relation
    dp[i] = min(dp[i],dp[i-coin]) when i-coin >=0
    algo:
    * create array of size amount+1, fill with amount+1
    * make dp[0]=0, iterate from i=1 to i<=amount
    * iterate over coins, if i-coin >=0 then update dp[i] = min(dp[i],dp[i-coin]+1)
    * return dp[amount] at the end
    time & space:
    * it takes s space and s * n time
     */

    public int coinChange(int[] coins, int amount) {
        return coinChangeA1(coins,amount);
    }

    public int coinChangeA3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int coin:coins){
                if((i-coin)>=0){
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount]==(amount+1)?-1:dp[amount];
    }

    public int coinChangeA2(int[] coins, int amount) {
        return rec(coins, amount);
    }

    Map<Integer,Integer> map = new HashMap<>();
    public int rec(int[] coins, int rem){
        if(rem==0)return 0;
        if(rem<0)return -1;
        if(map.containsKey(rem))return map.get(rem);
        int count = Integer.MAX_VALUE;
        for(int coin : coins){
            int res = rec(coins,rem-coin);
            if(res !=-1) count = Math.min(count,res+1);
        }
        map.put(rem,count!=Integer.MAX_VALUE?count:-1);
        return map.get(rem);
    }

    public int coinChangeA1(int[] coins, int amount) {
        return rec1(coins, amount);
    }

    public int rec1(int[] coins, int rem){
        if(rem==0)return 0;
        if(rem<0)return -1;
        int count = Integer.MAX_VALUE;
        for(int coin : coins){
            int res = rec1(coins,rem-coin);
            if(res !=-1) count = Math.min(count,res+1);
        }
        return count!=Integer.MAX_VALUE?count:-1;
    }
}
