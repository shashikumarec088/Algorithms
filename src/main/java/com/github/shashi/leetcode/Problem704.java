package com.github.shashi.leetcode;

public class Problem704 {
    public int search(int[] nums, int target) {
        return searchA1(nums, target);
    }

    public int searchA1(int[] nums, int target) {
        int n = nums.length;
        int l=0, r=n-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(target == nums[mid])return mid;
            else if(target<nums[mid]) r = mid-1;
            else l = mid+1;
        }
        return -1;
    }
}