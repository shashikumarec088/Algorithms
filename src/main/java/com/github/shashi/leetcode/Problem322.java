package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem322 {
    private int[] coins;
    private Integer[] cache;
    public int coinChange(int[] coins, int amount) {
        return coinChangeA2(coins,amount);
    }

    public int coinRec(int[] coins, int amount){
        if(amount==0) return 0;
        this.coins = coins;
        this.cache = new Integer[amount+1];
        return dp(amount);
    }

    public int coinChangeA2(int[] coins, int amount){
        int[] dp = new int[amount+1];
        dp[0]=0;
        for(int i=1; i<= amount; i++){
            int min = amount+1;
            for(int coin: coins)
                if(i-coin >=0)
                    min = Math.min(min, dp[i-coin]+1);
            dp[i] = min;
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

    public int dp(int amount){
        if(amount == 0) return 0;
        if(amount < 0) return -1;
        if(cache[amount] == null){
            int minCoins = -1;
            for(int coin : coins){
                int count = dp(amount - coin);
                if(count != -1){
                    if(minCoins == -1)
                        minCoins = 1+count;
                    else minCoins = Math.min(minCoins,1+count);
                }
            }
            cache[amount] = minCoins;
        }
        return cache[amount];
    }
}
