package com.github.shashi.leetcode;

public class Problem154 {
    public int findMin(int[] nums) {
        return findMinA2(nums);
    }

    public int findMinA2(int[] nums) {
        int l=0, r = nums.length-1, n = nums.length;
        while(l<r){
            int mid = l +(r-l)/2;
            if(nums[mid]==nums[r])r--;
            else if(nums[mid]>nums[r])l=mid+1;
            else r=mid;
        }
        return nums[l];
    }

    public int findMinA1(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int num : nums)
            if(num<min)min=num;
        return min;
    }
}