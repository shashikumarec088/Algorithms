package com.github.shashi.leetcode;

public class Problem53 {
    public static void main(String[] args) {
        Problem53 problem53 = new Problem53();
        int[] input = {-1,-2};
        problem53.maxSubArray(input);
    }
    public int maxSubArray(int[] nums) {
        return maxSubArrayA4(nums);
    }

    public int maxSubArrayA4(int[] nums){
        return rec(nums,0, nums.length-1);
    }

    public int rec(int[] nums, int start, int end){
        if(start>end)return Integer.MIN_VALUE;
        int mid = Math.floorDiv(start+end,2);
        int lsum=0, lcsum=0, rsum=0, rcsum=0;
        for(int i=mid-1; i>=start; i--){
            lcsum+=nums[i];
            lsum = Math.max(lsum,lcsum);
        }
        for(int i=mid+1; i<=end; i++){
            rcsum += nums[i];
            rsum = Math.max(rsum,rcsum);
        }
        int combined = lsum+rsum+nums[mid];
        int left = rec(nums,start,mid-1);
        int right = rec(nums, mid+1,end);
        return Math.max(Math.max(left,right),combined);
    }

    public int maxSubArrayA3(int[] nums){
        int n=nums.length, sum=nums[0],prev=nums[0];
        for(int i=1; i<n; i++){
            prev = Math.max(prev+nums[i],nums[i]);;
            sum = Math.max(sum,prev);
        }
        return sum;
    }

    public int maxSubArrayA2(int[] nums){
        int n=nums.length, sum=nums[0];
        int[] dp = new int[n];
        dp[0]=sum;
        for(int i=1; i<n; i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);;
            sum = Math.max(sum,dp[i]);
        }
        return sum;
    }

    public int maxSubArrayA1(int[] nums){
        int n=nums.length, sum=Integer.MIN_VALUE;
        int[] nSum = new int[n];
        nSum[0] = nums[0];
        for(int i=1; i<n; i++)
            nSum[i] = nSum[i-1]+nums[i];
        for(int i=0; i<n; i++){
            int cSum=0;
            for(int j=i; j<n; j++){
                cSum += nums[i];
                sum = Math.max(sum,cSum);
            }
        }
        return sum;
    }
}