package com.github.shashi.leetcode;

public class Problem45 {
    public int jump(int[] nums) {
        return jumpA3(nums);
    }

    public int jumpA3(int[] nums) {
        int prev=0, cur=0,j=0,n=nums.length;
        if(n==1)return 0;
        for(int i=0; i<n;i++){
            cur = Math.max(cur,i+nums[i]);
            // inc jumps as max we can reach is still prev jump value
            if(i==prev){
                j++;
                prev=cur;
            }
            if(prev >=n-1)break;
        }
        return j;
    }

    public int jumpA2(int[] nums) {
        Integer[] dp =new Integer[nums.length];
        return rec(nums,0,dp);
    }

    /*
    intuition is same as jump1 but since we need to find the min
    jumps we need to iterate all distances and then update the min value
    */
    public int rec(int[] nums, int i,Integer[] dp){
        if(i>= nums.length-1)return 0;
        if(dp[i]!=null)return dp[i];
        int min = Integer.MAX_VALUE;
        int max =Math.min(i+nums[i],nums.length-1);
        for(int j=max; j>i; j--){
            min = Math.min(min,rec(nums,j,dp));
        }
        if(min!=Integer.MAX_VALUE) {
            dp[i] = min+1;
        }
        else dp[i] = min;
        return dp[i];
    }

    /*
    dp solution from top down similar to jump1
    */
    public int jumpA1(int[] nums) {
        int n=nums.length;
        Integer[] dp = new Integer[n];
        dp[n-1] = 0;
        for(int i=n-2; i>=0; i--){
            int min = Integer.MAX_VALUE;
            int max = Math.min(i+nums[i],n-1);
            for(int j=max; j>i; j--){
                if(dp[j]!=null){
                    min = Math.min(min,dp[j]);
                }
            }
            if(min!=Integer.MAX_VALUE)
                dp[i]=min+1;
        }
        return dp[0];
    }
}