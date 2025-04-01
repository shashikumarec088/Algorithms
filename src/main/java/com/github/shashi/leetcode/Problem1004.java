package com.github.shashi.leetcode;

public class Problem1004 {
    /*
    1004. Max Consecutive Ones III
    Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.



    Example 1:

    Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    Output: 6
    Explanation: [1,1,1,0,0,1,1,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
    Example 2:

    Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    Output: 10
    Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


    Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.
    0 <= k <= nums.length

    Approach 1: 2 pointer approach
    * intuition is to use windowing technique by allowing max k zeros when k<=0 then stop update
    max and shrink the window
    algo:
    * init i=0,j=0,max=0
    * iterate till j<n
        * iterate till j<n && k>0
            * if nums[j]==0 then dec k
            * inc j
        * update max = max(j-i,max)
        * iterate till i<j and k<=0
            * if nums[j] == 0 inc k
            * inc i
    * return max at the end
    time & space:
    * it takes n time and const space

    approach 2: similar to approach 1 but only 1 while
    algo:
    * init i=0,zeros=0,n=length,max=0;
    * iterate j=0 to n
    * count zeros
    * while zeros > k
        * if nums[i] == 0 dec zeros
        * inc i
    * update max = max(j-i+1,max) here +1 is to count the number of elements 0 to 2 means 3 numbers
    * return max
    time & space:
    * it takes n time and const space
     */

    public int longestOnes(int[] nums, int k) {
        return longestOnesA1(nums,k);
    }

    public int longestOnesA1(int[] nums, int k) {
        int n = nums.length,i=0,j=0,max=0;
        while(j<n){
            while(j<n && (nums[j]==1 || k>0)){
                if(nums[j]==0)k--;
                j++;
            }
            max=Math.max(j-i,max);
            while(i<n && k<=0){
                if(nums[i]==0)k++;
                i++;
            }
        }
        return max;
    }

    public int longestOnesA2(int[] nums,int k) {
        int i=0,max=0,zeros=0,n=nums.length;
        for(int j=0;j<n;j++){
            zeros+= nums[j]==0?1:0;
            while(zeros>k){
                if(nums[i]==0)zeros--;
                i++;
            }
            max = Math.max(max,j-i+1);
        }
        return max;
    }
}
