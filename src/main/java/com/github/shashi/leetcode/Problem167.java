package com.github.shashi.leetcode;

public class Problem167 {
    public int[] twoSum(int[] numbers, int target) {
        return twoSumSol1(numbers,target);
    }

    public int[] twoSumSol1(int[] nums, int target){
        int i=0,j=nums.length-1;
        while(i<j){
            int sum = nums[i]+nums[j];
            if(sum==target)
                return new int[]{i+1,j+1};
            else if(sum<target)i++;
            else j--;
        }
        return new int[]{-1,-1};
    }
}
