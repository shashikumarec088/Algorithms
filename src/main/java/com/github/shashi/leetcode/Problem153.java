package com.github.shashi.leetcode;

public class Problem153 {
    /*
    Find Minimum in Rotated Sorted Array
    Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array
    nums = [0,1,2,4,5,6,7] might become:
    [4,5,6,7,0,1,2] if it was rotated 4 times.
    [0,1,2,4,5,6,7] if it was rotated 7 times.
    Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1],
    a[2], ..., a[n-2]].
    Given the sorted rotated array nums of unique elements, return the minimum element of this array.
    You must write an algorithm that runs in O(log n) time.
    Example 1:
    Input: nums = [3,4,5,1,2]
    Output: 1
    Explanation: The original array was [1,2,3,4,5] rotated 3 times.
    Example 2:
    Input: nums = [4,5,6,7,0,1,2]
    Output: 0
    Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
    Example 3:
    Input: nums = [11,13,15,17]
    Output: 11
    Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
    Constraints:
    n == nums.length
    1 <= n <= 5000
    -5000 <= nums[i] <= 5000
    All the integers of nums are unique.
    nums is sorted and rotated between 1 and n times.

    Approach 1: binary search with right boundary comparision
    * intuition is to use the binary search and the fact that in rotated sorted array if mid element > last element
    then min element lies on right side of the fact else on the left side including mid.
    algo:
    * init l=0, r=n-1 where n is number of elements
    * iterate until l<r calculate mid=l+(r-l)/2, if nums[mid]>nums[n-1] then make l=mid+1 else r=mid
    * return nums[l] at the end
    * here we use condition l<r not l<=r because we consider the mid-element on left part of the binary search
    and we will be left with 1 element to process that will  the desired element.
    time & space:
    * it takes log n time and constant space
     */
    public static void main(String[] args) {
        int[] input = new int[]{4,5,6,7,0,1,2};
        Problem153 problem153 = new Problem153();
        System.out.println(problem153.findMin(input));
    }

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
        return findMinA1(nums);
    }
}
