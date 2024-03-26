package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem81 {
    public boolean search(int[] nums, int target) {
        return searchA1(nums,target);
    }

    /*
    *intuition is if array has duplicates we wont be able to judge which part of the
    * array is sorted or not if the mid equal to right,
    * if mid is greater than right then left part is sorted or if smaller than right
    * then right part is sorted, else we need to dec the r by 1
    *
     */
    public boolean searchA1(int[] nums, int target) {
        int l=0, r = nums.length-1, n = nums.length-1;
        Set<Integer> set = new HashSet<>();
        while(l<=r){
            int mid = l +(r-l)/2;
            if(nums[mid]==target)return true;
            else if(nums[mid] > nums[r]){
                if(target >= nums[l] && target < nums[mid])
                    r=mid-1;
                else l=mid+1;
            }else if(nums[mid] < nums[r]){
                if(target > nums[mid] && target <= nums[r])
                    l=mid+1;
                else r=mid-1;
            }else r--;
        }
        return false;
    }
}