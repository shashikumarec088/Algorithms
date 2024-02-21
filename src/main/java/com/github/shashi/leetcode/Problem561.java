package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem561 {
    public int arrayPairSum(int[] nums) {
        return arrayPairSumA2(nums);
    }

    public int arrayPairSumA2(int[] nums){
        int[] freqs = new int[20001];
        for(int num : nums)
            freqs[10000+num]++;
        int sum=0;
        boolean even=true;
        for(int i=0; i<20001;i++){
            while(freqs[i]>0){
                if(even) sum += i-10000;
                freqs[i]--;
                even = !even;
            }
        }
        return sum;
    }

    public int arrayPairSumA1(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for(int i=0; i<nums.length; i+=2){
            sum += Math.min(nums[i],nums[i+1]);
        }
        return sum;
    }
}