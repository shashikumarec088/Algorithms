package com.github.shashi.leetcode;

public class Problem153 {
    public static void main(String[] args) {
        int[] input = new int[]{4,5,6,7,0,1,2};
        Problem153 problem153 = new Problem153();
        System.out.println(problem153.findMin(input));
    }
    /*
    this is more intutive when the mid is > the rightmost element then our small
    element will be on the right side else it will be on the left side, we need to
    consider mid else because that itself might be the smallest, consider [1,2]
    mid will be 0, 
     */
    public int findMinA1(int[] nums) {
        int l=0, r = nums.length-1, n = nums.length;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[n-1])l=mid+1;
            else r=mid;
        }
        return nums[l];
    }


    public int findMin(int[] nums){
        int l=0,r=nums.length-1;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]>nums[l])
                l=mid+1;
            else r = mid;
        }
        return l;
    }
}
