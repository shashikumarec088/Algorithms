package com.github.shashi.leetcode;

public class Problem977{
    public int[] sortedSquares(int[] nums) {
        return sortedSquaresA1(nums);
    }

    public int[] sortedSquaresA1(int[] nums){
        int n = nums.length;
        int l=0,r=n-1,i=n-1;
        int[] ans = new int[n];
        while(l <=r){
            int lNum = Math.abs(nums[l]);
            int rNum = Math.abs(nums[r]);
            if(lNum >= rNum){
                l++;
                ans[i]= lNum*lNum;
            }else{
                r--;
                ans[i] = rNum*rNum;
            }
            i--;
        }
        return ans;
    }
}
