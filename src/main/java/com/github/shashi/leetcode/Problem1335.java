package com.github.shashi.leetcode;
import java.util.*;
public class Problem1335 {
    private int n,d;
    private int[] difficultJob;
    private int[] jobDifficulty;
    private int[][] memo;
    public int minDifficulty(int[] jobDifficulty, int d) {
        return minDiff(jobDifficulty,d);
    }

    public int minDiff(int[] jobDifficulty, int d){
        this.n = jobDifficulty.length;
        if(n<d) return -1;
        this.d = d;
        this.jobDifficulty = jobDifficulty;
        this.difficultJob = new int[n];
        this.memo = new int[n][d+1];
        int hard = 0;
        for(int i= n-1; i>=0; i--){
            hard = Math.max(hard,jobDifficulty[i]);
            difficultJob[i] = hard;
        }
        for(int i=0; i<n; i++)
            Arrays.fill(memo[i],-1);
        return dp(0,1);
    }

    public int dp(int i, int day){
        if(day == d) return difficultJob[i];
        if(memo[i][day] == -1){
            int hard=0, best = Integer.MAX_VALUE;
            for(int j=i; j< n-d+day; j++){
                hard = Math.max(hard,jobDifficulty[j]);
                best = Math.min(best,hard+dp(j+1,day+1));
            }
            memo[i][day]=best;
        }
        return memo[i][day];
    }
}
