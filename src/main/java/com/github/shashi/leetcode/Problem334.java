package com.github.shashi.leetcode;

public class Problem334 {
    /*
    334. Increasing Triplet Subsequence
    Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and
    nums[i] < nums[j] < nums[k]. If no such indices exists, return false.



    Example 1:

    Input: nums = [1,2,3,4,5]
    Output: true
    Explanation: Any triplet where i < j < k is valid.
    Example 2:

    Input: nums = [5,4,3,2,1]
    Output: false
    Explanation: No triplet exists.
    Example 3:

    Input: nums = [2,1,5,0,4,6]
    Output: true
    Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.


    Constraints:

    1 <= nums.length <= 5 * 105
    -231 <= nums[i] <= 231 - 1


    Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?

    Approach 1: single scan with 2 variables
    * intuition is to have 2 variables to store the minimum and second minimum values, iterate over
    the numbers and update 1st if cur number is smaller then first else if smaller than 2nd then update
    second else return true if cur number is greater than 2nd
    algo:
    * init first with Integer.MAX_VALUE and second with Integer.MAX_VALUE
    * iterate over the array from 0 to n-1
    * if cur number is smaller than first then update first
    * else if cur number is smaller than second then update second
    * else return true if cur number is greater than second
    * return false at the end
    time & space:
    * n time and 1 space
     */

    public boolean increasingTriplet(int[] nums) {
        return increasingTripletA1(nums);
    }

    public boolean increasingTripletA1(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num: nums){
            if(num<=first)
                first=num;
            else if(num<=second)
                second = num;
            else return true;
        }
        return false;
    }
}
