package com.github.shashi.leetcode;

public class Problem34 {
    /*
    Find First and Last Position of Element in Sorted Array
    Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given
    target value.
    If target is not found in the array, return [-1, -1].
    You must write an algorithm with O(log n) runtime complexity.
    Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
    Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]
    Example 3:
    Input: nums = [], target = 0
    Output: [-1,-1]
    Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -109 <= target <= 109

    Approach 1: 2 binary search
    * intuition is to do the binary search 2 times first to find the left element and then to find the last element.
    while finding the first position when we find the target we search for the next first position on the left side
    .while finding the right position when we find the position of target then we keep searching on the right side.
    algo:
    * init ans -1,-1 and l=0, r=n-1, if n is 0 then we return ans
    * we need to find the first position of target, when finding the target in binary search when we find the target
    we return the position, here instead we update the start position as possible ans and keep searching for target
    in the left half.
    * in normal bs the condition is l<=r so we start the while loop with this condition and find mid if num at mid
    is less than target then we make l=mid+1 else if > target then we make r=mid-1
    * if nums at mid is equal to target then we update the start position ans[0]=mid and make r=mid-1. while
    searching for the right position we update the ans[1] = mid and make l=mid+1 and rest is same
    * return ans at the end
    time & space:
    * it takes log n time and const space
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};
        int n = nums.length;
        if(n==0)return ans;
        int l=0, r = n-1;
        while(l <= r){
            int mid = l+(r-l)/2;
            if(nums[mid]<target) l=mid+1;
            else if(nums[mid]>target)r=mid-1;
            else{
                ans[0]=mid;
                r=mid-1;
            }
        }
        l=0;
        r = n-1;
        while(l <= r){
            int mid = l+(r-l)/2;
            if(nums[mid]<target) l=mid+1;
            else if(nums[mid]>target)r=mid-1;
            else{
                ans[1]=mid;
                l=mid+1;
            }
        }
        return ans;
    }
}