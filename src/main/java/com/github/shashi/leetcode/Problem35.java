package com.github.shashi.leetcode;

public class Problem35 {
    /*
    Search Insert Position
    Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
    return the index where it would be if it were inserted in order.
    You must write an algorithm with O(log n) runtime complexity.
    Example 1:
    Input: nums = [1,3,5,6], target = 5
    Output: 2
    Example 2:
    Input: nums = [1,3,5,6], target = 2
    Output: 1
    Example 3:
    Input: nums = [1,3,5,6], target = 7
    Output: 4
    Constraints:
    1 <= nums.length <= 104
    -104 <= nums[i] <= 104
    nums contains distinct values sorted in ascending order.
    -104 <= target <= 104

    Approach 1: binary search
    * this is classic binary search problem
    algo:
    * initialize l=0, r=n-1, iterate while loop until l<=r
    * at each iteration compare if element at mid is equal to target then return mid
    * if element at mid is lower than target then make l=mid+1 ele r = mid-1
    * return l at the end indicating no target is found and we need to insert the element at the end
    time & space:
    * it takes log n time and constant space
     */
    public int searchInsert(int[] nums, int target) {
        return searchInsertA1(nums,target);
    }

    public int searchInsertA1(int[] nums, int target) {
        int l=0,n=nums.length,r=nums.length-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]<target)l=mid+1;
            else r=mid-1;
        }
        return l;
    }
}
