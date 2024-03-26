package com.github.shashi.leetcode;

public class Problem167 {
    public int[] twoSum(int[] numbers, int target) {
        return towSumA3(numbers, target);
    }

    /*
    * same as A3 but just mentioned the conditions explicitly
     */
    public int[] twoSumA4(int[] numbers, int target) {
        int i=0, j= numbers.length-1,n=numbers.length;
        while(i<=j){
            int mid = i+(j-i)/2;
            if(mid!= i  && numbers[mid]+numbers[i]==target)return new int[]{i+1,mid+1};
            else if (mid!= i && numbers[mid]+numbers[i]>target){
                j=mid-1;
                continue;
            }
            int sum = numbers[i]+numbers[j];
            if(sum== target)return new int[]{i+1,j+1};
            if(sum<target)i++;
            else j--;
        }
        return new int[]{i,j};
    }
    public int[] towSumA3(int[] nums, int target){
        int l=0, r=nums.length-1;
        while(l<r){
            int sum = nums[l]+nums[r];
            int mid = l+(r-l)/2;
            if(sum==target)return new int[]{l+1,r+1};
            else if(sum<target)
                l = nums[r]+nums[mid]<target?mid:l+1;
            else r = nums[l]+nums[mid]>target?mid:r-1;
        }
        return new int[]{-1,-1};
    }

    public int binarySearch(int[] nums, int target, int start, int end){
        while(start<= end){
            int mid = start+(end-start)/2;
            if(nums[mid]==target)return mid;
            else if(target>nums[mid])start=mid+1;
            else end=mid-1;
        }
        return -1;
    }

    public int[] twoSumA2(int[] nums, int target){
        int l=0, r=nums.length-1;
        while(l<r){
            int sum = nums[l]+nums[r];
            if(sum==target)return new int[]{l+1,r+1};
            else if(sum>target)r--;
            else l++;
        }
        return new int[]{-1,-1};
    }

    public int[] twoSumA1(int[] nums, int target){
        int n = nums.length;
        for(int i=0; i<n; i++){
            int oi = binarySearch(nums,target-nums[i], i+1, n-1);
            if(oi!=-1)
                return new int[]{i+1,oi+1};
        }
        return new int[]{-1,-1};
    }
}
