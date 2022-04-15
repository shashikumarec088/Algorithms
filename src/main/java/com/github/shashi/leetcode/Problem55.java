package com.github.shashi.leetcode;

public class Problem55 {
    public boolean canJump(int[] nums) {
        return canJumpA3(nums);
    }

    public boolean canJumpA3(int[] nums){
        int n=nums.length;
        int pos = n-1;
        for(int i=n-1; i>=0; i--)
            if(i+nums[i]>=pos)
                pos=i;
        return pos==0;
    }

    public boolean canJumpA1(int[] nums){
        Boolean[] memo = new Boolean[nums.length];
        return rec(nums,0,memo);
    }

    public boolean canJumpA2(int[] nums){
        int n=nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1]=true;
        for(int i=n-2; i>=0; i--){
            int l = Math.min(i+nums[i],n-1);
            for(int j=i+1;j<=l; j++){
                if(dp[j]){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public boolean rec(int[] nums,int i, Boolean[] memo){
        if(i+1==nums.length)return true;
        if(i>= nums.length)return false;
        if(memo[i]!= null)return memo[i];
        boolean found = false;
        for(int j=1; j<=nums[i]; j++)
            if(rec(nums,i+j,memo)){
                found=true;
                break;
            }
        return memo[i]=found;
    }
}