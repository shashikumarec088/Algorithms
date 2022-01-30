package com.github.shashi.leetcode;
import  java.util.*;
public class Problem16 {
    public int threeSumClosest(int[] nums, int target) {
        return threeCloset(nums,target);
    }
    public int threeSumBin(int[] nums, int target){
        int sum=0, n= nums.length;
        Arrays.sort(nums);
        for(int i=0; i<n-2; i++){
            sum+= sumTwo(nums,i+1,target-nums[i]);
        }
        return sum;
    }

    public int sumTwo(int[] nums, int i, int target){
        int sum=0,n=nums.length;
        for(int j=i;j<n-1;j++)
            sum+= bin(nums,j,target-nums[j])-j;
        return sum;
    }

    public int bin(int[] nums, int i, int target){
        int l=i, h = nums.length-1;
        while(l<h){
            int mid = (h+l+1)/2;
            if(nums[mid]<target)l=mid;
            else h=mid-1;
        }
        return l;
    }
    public int threeCloset(int[] nums, int target){
        int diff= Integer.MAX_VALUE,closestSum = 0, n = nums.length;
        Arrays.sort(nums);
        for(int i=0; i<n; i++){
            int l=i+1, h = n-1;
            while(l<h){
                int sum = nums[i]+nums[l]+nums[h];
                if(sum== target) return target;
                int newDiff = Math.abs(sum-target);
                if(newDiff<diff){
                    diff = newDiff;
                    closestSum = sum;
                }
                if(sum < target)l++;
                else h--;
            }
        }
        return closestSum;
    }
}
