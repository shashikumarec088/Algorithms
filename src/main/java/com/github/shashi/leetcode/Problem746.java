package com.github.shashi.leetcode;
import java.util.*;
public class Problem746 {
    Map<Integer,Integer> map = new HashMap<>();
    public int minCostClimbingStairs(int[] cost) {
        return dpItr2(cost);
    }

    public int minCostDp(int[] cost){
        return dp(cost, cost.length);
    }

    public int dpItr2(int[] cost){
        int down1 = 0, down2 =0,total=0;
        for(int i=2; i<=cost.length; i++){
            total = Math.min(down1+cost[i-1],down2+cost[i-2]);
            down2 = down1;
            down1 = total;
        }

        return total;
    }

    public int dpItr(int[] cost){
        int[] totalCost = new int[cost.length+1];
        totalCost[0]=0;
        totalCost[1]=0;
        for(int i=2; i<totalCost.length; i++)
            totalCost[i] = Math.min(totalCost[i-1]+cost[i-1],
                    totalCost[i-2]+cost[i-2]
            );
        return totalCost[cost.length];
    }

    public int dp(int[] cost, int n){
        if(n<=1) return 0;
        if(!map.containsKey(n))
            map.put(n,Math.min(dp(cost,n-1)+cost[n-1],dp(cost,n-2)+cost[n-2]));
        return map.get(n);
    }
}
