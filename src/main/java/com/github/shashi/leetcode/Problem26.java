package com.github.shashi.leetcode;

public class Problem26 {
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesA1(nums);
    }

    public int removeDuplicatesA2(int[] nums) {
        int s=0, f=1;
        while(f<nums.length){
            if(nums[s]!=nums[f]){
                nums[++s] = nums[f];
            }
            f++;
        }
        return s+1;
    }

    public int removeDuplicatesA1(int[] nums) {
        int s=0, f=1;
        while(f<nums.length){
            while(f<nums.length && nums[s]==nums[f])f++;
            if(f<nums.length){
                s++;
                nums[s]=nums[f];
                f++;
            }
        }
        return s+1;
    }
}