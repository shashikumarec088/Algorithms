package com.github.shashi.leetcode;

public class Problem153 {
    public static void main(String[] args) {
        int[] input = new int[]{4,5,6,7,0,1,2};
        Problem153 problem153 = new Problem153();
        System.out.println(problem153.findMin(input));
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
