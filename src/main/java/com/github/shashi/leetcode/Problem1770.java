package com.github.shashi.leetcode;

public class Problem1770 {
    private int n,m;
    private int[] nums;
    private int[] multipliers;
    private int[][] memo;
    public int maximumScore(int[] nums, int[] multipliers) {
        return maxScoreItr(nums,multipliers);
    }

    public int maxScoreDp(int[] nums, int[] multipliers){
        n = nums.length;
        m = multipliers.length;
        this.nums = nums;
        this.multipliers = multipliers;
        memo = new int[m][m];
        return dp(0,0);
    }

    public int maxScoreItr(int[] nums, int[] multipliers){
        int m = multipliers.length, n = nums.length;
        int[][] memo = new int[m+1][m+1];
        for(int i=m-1; i>=0; i--){
            for(int left=i; left>=0; left--){
                int right = n - 1 - (i-left);
                int mult = multipliers[i];
                memo[i][left] = Math.max(mult*nums[left]+memo[i+1][left+1],
                        mult*nums[right]+memo[i+1][left]);
            }
        }
        return memo[0][0];
    }

    public int dp(int i, int left){
        if(i == m) return 0;
        int mult = multipliers[i];
        int right = n - 1 - (i-left);
        if(memo[i][left] ==0)
            memo[i][left] = Math.max(mult*nums[left]+dp(i+1,left+1),mult*nums[right]+dp(i+1,left));
        return memo[i][left];
    }
}
