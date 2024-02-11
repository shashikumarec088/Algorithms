package com.github.shashi.leetcode;

public class Problem724 {
    public int pivotIndex(int[] nums) {
        return pivotIndexA2(nums);
    }

    public int pivotIndexA2(int[] nums){
        int sum=0, leftSum=0;
        for(int num : nums)sum+=num;
        for(int i=0; i<nums.length;i++){
            if(leftSum == (sum-leftSum - nums[i])) return i;
            leftSum += nums[i];
        }
        return -1;
    }

    public int pivotIndexA1(int[] nums) {
        int n = nums.length;
        int[] rsums = new int[n];
        rsums[n-1] = nums[n-1];
        for(int i=nums.length-2; i>=0; i--){
            rsums[i] = nums[i]+rsums[i+1];
        }
        int ls=0;
        for(int i=0; i<n;i++){
            ls+= nums[i];
            if(ls== rsums[i])return i;
        }
        return -1;
    }
}
