package com.github.shashi.leetcode;

public class Problem33 {
    public int search(int[] nums, int target) {
        return searchA2(nums,target);
    }

    /*
     *  intuition, hold true when array does not contains duplicates
     * if mid is >= left then left part is sorted else right part
     */
    public int searchA2(int[] nums, int target) {
        int l=0, n = nums.length, r=nums.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]>=nums[l]){
                if(target >= nums[l] && target < nums[mid])
                    r=mid-1;
                else l=mid+1;
            }else{
                if(target > nums[mid] && target <= nums[r])
                    l=mid+1;
                else r=mid-1;
            }
        }
        return -1;
    }

    /*
     * find the min element using the binary search this bin
     * works when array does not have the duplicates
     * then find the element on both the parts using the binary search
     */
    public int searchA1(int[] nums, int target) {
        int l=0, n = nums.length, h=nums.length-1;
        while(l<=h){
            int mid = l+(h-l)/2;
            if(nums[mid]>nums[n-1])
                l=mid+1;
            else h=mid-1;
        }
        int ans = bin(nums,0,l-1,target);
        if(ans!=-1)return ans;
        return bin(nums, l,n-1,target);
    }

    public int bin(int[] nums, int l, int h, int target){
        while(l<=h){
            int mid = l+(h-l)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]<target)l=mid+1;
            else h=mid-1;
        }
        return -1;
    }


}