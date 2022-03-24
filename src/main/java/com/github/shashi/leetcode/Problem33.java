package com.github.shashi.leetcode;

public class Problem33 {
    public int search(int[] nums, int target) {
        return searchA2(nums,target);
    }

    public int searchA2(int[] nums, int target){
        int n = nums.length;
        if(n==1)return nums[0]==target?0:-1;
        int s = 0, e = n-1;
        while(s<=e){
            int m = s+(e-s)/2;
            if(nums[m]==target)return m;
            else{
                if(nums[m]>=nums[s]){
                    if(target>=nums[s]&& target<nums[m])
                        e=m-1;
                    else s=m+1;
                }else{
                    if(target<=nums[e]&&target>nums[m])
                        s=m+1;
                    else e=m-1;
                }
            }
        }
        return -1;
    }

    public int searchA1(int[] nums, int target){
        int n = nums.length;
        if(n==1)return nums[0]==target?0:-1;
        int pivot = getStart(nums,0,n-1);
        if(pivot==0)return searchBin(nums,0,n-1,target);
        else if(target>=nums[0])return searchBin(nums, 0, pivot-1,target);
        else return searchBin(nums,pivot,n-1,target);
    }
    public int getStart(int[] nums, int start, int end){
        if(nums[start]<nums[end])return 0;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(nums[mid]>nums[mid+1])return mid+1;
            else if(nums[mid]>=nums[start])
                start=mid+1;
            else end=mid-1;
        }
        return -1;
    }

    public int searchBin(int[] nums, int start, int end, int target){
        while(start<=end){
            int mid=start+(end-start)/2;
            if(nums[mid]==target)return mid;
            if(target>nums[mid])start=mid+1;
            else end = mid-1;
        }
        return -1;
    }
}