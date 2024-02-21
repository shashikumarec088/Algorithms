package com.github.shashi.leetcode;

public class Problem209 {
    public int minSubArrayLen(int target, int[] nums) {
        return minSubArrayLenA1(target, nums);
    }

    public int minSubArrayLenA1(int target, int[] nums) {
        int i=0, j=0, sum=0,l=0;
        while(i<=j && j< nums.length){
            sum += nums[j];
            while( sum >= target){
                l = l==0?j-i+1:Math.min(l, j-i+1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return l;
    }
}