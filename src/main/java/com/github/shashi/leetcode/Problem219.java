package com.github.shashi.leetcode;

public class Problem219 {
    /*
    Contains Duplicate II
    Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such
    that nums[i] == nums[j] and abs(i - j) <= k.

    Example 1:
    Input: nums = [1,2,3,1], k = 3
    Output: true
    Example 2:
    Input: nums = [1,0,1,1], k = 1
    Output: true
    Example 3:
    Input: nums = [1,2,3,1,2,3], k = 2
    Output: false

    Constraints:
    1 <= nums.length <= 105
    -109 <= nums[i] <= 109
    0 <= k <= 105

    Approach 1:
    * intuition is to check all the sub array positions such that the difference is <=k and check if elements are same
    then return true, return false at the end.
    algo:
    * iterate from i=0 to n and inner loop from j=i+1 to j-i<=k and j<n check of number exists
    time & space:
    * time is o(n min(n,k)) and no space

    Approach 2:
    * intuition is to keep the last k elements in the hashset and check if the current element is present in the set
    if so return true else add the current element is length exceeds k then remove the i-kth element from set since
    in the next iteration this element is not eligible as the will be outside the bound
    algo:
    * have hashset to store the elements iterate from i=0 to n check if current element in set if so return true
    * else add the current element, check if length exceeds k if so remove i-kth element from set
    * return false at the end
     */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        return containsNearbyDuplicateA1(nums,k);
    }

    public boolean containsNearbyDuplicateA1(int[] nums, int k) {
        int n=nums.length;
        for(int i=0; i<n; i++){
            for(int j=i+1; ((j-i<=k) && j<n);j++){
                if(nums[i]==nums[j])return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicateA2(int[] nums, int k) {
        int n=nums.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            if(set.contains(nums[i]))return true;
            set.add(nums[i]);
            if(set.size()>k)set.remove(nums[i-k]);
        }
        return false;
    }
}
