package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem905 {
    public int[] sortArrayByParity(int[] nums) {
        Arrays.copyOf(nums,nums.length);

        return sortArrayByParityA1(nums);
    }

    public int[] sortArrayByParityA1(int[] nums) {
        int n=nums.length;
        int i=0, j=n-1;
        while(i<j){
            if(nums[i]%2==0)i++;
            else{
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j--;
            }
        }
        return nums;
    }
}