package com.github.shashi.leetcode;

public class Problem162 {
    public int findPeakElement(int[] nums) {
        return findPeakElementA3(nums);
    }

    public int findPeakElementA3(int[] nums){
        int l = 0, r = nums.length-1;
        return rec(nums,l,r);
    }

    public int rec(int[] nums, int l, int r){
        if(l==r)return l;
        int mid = l+(r-l)/2;
        if(nums[mid]>nums[mid+1])return rec(nums,l,mid);
        return rec(nums,mid+1,r);
    }

    public int findPeakElementA2(int[] nums){
        int l=0, r=nums.length-1;
        while(l<r){
            int mid = l + (r-l)/2;
            if(nums[mid]>nums[mid+1])r=mid;
            else l=mid+1;
        }
        return l;
    }

    public int findPeakElementA1(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n-1; i++){
            if(nums[i] > nums[i+1]){
                return i;
            }
        }
        return n-1;

    }
}