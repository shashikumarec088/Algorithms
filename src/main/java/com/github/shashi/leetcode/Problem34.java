package com.github.shashi.leetcode;

public class Problem34 {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};
        int n = nums.length;
        if(n==0)return ans;
        int l=0, r = n-1;
        while(l <= r){
            int mid = l+(r-l)/2;
            if(nums[mid]<target) l=mid+1;
            else if(nums[mid]>target)r=mid-1;
            else{
                ans[0]=mid;
                r=mid-1;
            }
        }
        l=0;
        r = n-1;
        while(l <= r){
            int mid = l+(r-l)/2;
            if(nums[mid]<target) l=mid+1;
            else if(nums[mid]>target)r=mid-1;
            else{
                ans[1]=mid;
                l=mid+1;
            }
        }
        return ans;
    }
}