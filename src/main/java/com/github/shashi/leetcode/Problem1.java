package com.github.shashi.leetcode;
import java.util.*;
public class Problem1 {
    /*
    Two Sum

    Given an array of integers nums and an integer target, return indices of the two numbers such
    that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same
    element twice.
    You can return the answer in any order.

    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]
    Example 3
    Input: nums = [3,3], target = 6
    Output: [0,1]

    Constraints:
    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

    Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

    Approach 1:
    * intuition is to have a map to store the visited element and check at each element if the difference
    between the target and current element exists in the map if so then return the indexes.
    algo:
    * create the hashmap with integer, integer which hold value and the index.
    * iterate over the array check if target-cur exists in map if so return the indexes else add the
    cur to map
    * at the end return -1,-1
    time & space:
    * time complexity o(n) and space is o(n) for storing the indices

     */
    public int[] twoSum(int[] nums, int target) {
        return twoSumMap(nums,target);
    }

    public int[] twoSumMap(int[] nums, int target){
        int n = nums.length;
        int[] out = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                out[1]=i;
                out[0]= map.get(diff);
            }
            map.put(nums[i],i);
        }
        return out;
    }
}
