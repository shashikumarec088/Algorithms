package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem136 {
    /*
    Single Number
    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
    You must implement a solution with a linear runtime complexity and use only constant extra space.
    Example 1:
    Input: nums = [2,2,1]
    Output: 1
    Example 2:
    Input: nums = [4,1,2,1,2]
    Output: 4
    Example 3:
    Input: nums = [1]
    Output: 1
    Constraints:
    1 <= nums.length <= 3 * 104
    -3 * 104 <= nums[i] <= 3 * 104
    Each element in the array appears twice except for one element which appears only once.

    Approach 1:
    * intuition is to use the fact that xor of same numbers is 0 if we do xor on all numbers in list
    we end up with the unique number
    algo:
    * init ans=nums[0], iterate from i=1 to n
    * for each iteration make ans ^= nums[i]
    * return ans at the end
    time & space:
    * n time and const space

    Approach 2:
    * intuition is to use the set to track the uniques, have total sum and subtract it from two times the sum of
    unique elements we get the remaining element
    algo:
    * init totalSum, uniqueSum and set of integers
    * iterate over all nums
    * if num is not in set add to set and add to uniqueSum
    * add each num to totalSum
    * return 2*uniqueSum - totalSum
    time & space:
    * n time and n space;
     */

    public int singleNumber(int[] nums) {
        return singleNumberA2(nums);
    }

    public int singleNumberA1(int[] nums) {
        int ans=nums[0];
        for(int i=1; i<nums.length;i++){
            ans ^= nums[i];
        }
        return ans;
    }

    public int singleNumberA2(int[] nums) {
        int sumUnique=0,sumTotal=0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(!set.contains(num)){
                sumUnique+=num;
                set.add(num);
            }
            sumTotal+=num;
        }
        return 2*sumUnique - sumTotal;
    }
}
