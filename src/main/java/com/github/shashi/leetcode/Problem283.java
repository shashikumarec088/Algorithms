package com.github.shashi.leetcode;

public class Problem283 {
    /*
    283. Move Zeroes

    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

    Note that you must do this in-place without making a copy of the array.



    Example 1:

    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]
    Example 2:

    Input: nums = [0]
    Output: [0]


    Constraints:

    1 <= nums.length <= 104
    -231 <= nums[i] <= 231 - 1

    Follow up: Could you minimize the total number of operations done?

    Approach 1: interative 2 pointer
    * intuition is to start from 0th index when num is not 0 then copy and inc position at the end
    make remaining positions zero
    algo:
    * init i=0, j=0, n = length
    * iterate until j < n
        * if value at j !=0
            * copy and inc i
        * inc j
    * iterate until i< n
        * make value at i = 0
    time & space:
        * it takes n time and const space

    Approach 2: same as approach 1 with 1 loop
    * intuition is we can reset the value at j to 0 once we copied but we should make sure that the resetting
    position is not same as copied solution

     */

    public void moveZeroes(int[] nums) {
        moveZeroesA1(nums);
    }


    public void moveZeroesA1(int[] nums) {
        int i=0, j=0, n = nums.length;
        while(j<n){
            if(nums[j]!=0){
                nums[i++]=nums[j];
            }
            j++;
        }
        while(i<n)nums[i++]=0;
    }

    public void moveZeroesA2(int[] nums) {
        int i=0, j=0, n = nums.length;
        while(j<n){
            if(nums[j]!=0){
                nums[i++]=nums[j];
                if(j != i-1)nums[j]=0;
            }
            j++;
        }
    }
}
