package com.github.shashi.leetcode;

public class Problem997 {
    public int[] sortedSquares(int[] nums) {
        return sortedSquaresA2(nums);
    }

    public int[] sortedSquaresA2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int l=0, i=n-1, r = n-1;
        while(l<=r){
            if(Math.abs(nums[l])>=Math.abs(nums[r])){
                ans[i--] = nums[l]*nums[l];
                l++;
            }else{
                ans[i--] = nums[r]*nums[r];
                r--;
            }
        }

        return ans;
    }

    public int[] sortedSquaresA1(int[] nums) {
        int n = nums.length, i=0,k=0;
        int[] ans = new int[n];

        while(n>1 && i<n && nums[i]<0)i++;
        int j=i-1;
        while(n>1 && i<n && j>-1){
            int j1 = Math.abs(nums[j]);
            if(nums[i]<=j1){
                ans[k++] = nums[i]*nums[i];
                i++;
            }else{
                ans[k++]=nums[j]*nums[j];
                j--;
            }
        }
        while(i<n){
            ans[k++] = nums[i]*nums[i];
            i++;
        }
        while(j>-1){
            ans[k++]=nums[j]*nums[j];
            j--;
        }
        return ans;
    }
}