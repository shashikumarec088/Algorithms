package com.github.shashi.leetcode;

public class Problem33 {
    /*
    Search in Rotated Sorted Array
    There is an integer array nums sorted in ascending order (with distinct values).
    Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
    such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
    For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
    or -1 if it is not in nums.
    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
    Example 2:
    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1
    Example 3:
    Input: nums = [1], target = 0
    Output: -1
    Constraints:
    1 <= nums.length <= 5000
    -104 <= nums[i] <= 104
    All values of nums are unique.
    nums is an ascending array that is possibly rotated.
    -104 <= target <= 104

    Approach 1: binary search for pivot and then finding the solution
    * intuition is to search for the pivot index which divides athe array into two sorted sub arrays and do the
    binary search in the both the sub arrays to find the target, to find the pivot in the rotated subarray we
    can use the fact that if mid element is > last element then pivot lies in mid+1 to last else on l to mid
    algo:
    * init l=0, r=n-1 where n is number of elements in array
    * iterate the while loop until l<r
    * mid = l+(r-l)/2, if mid is > nums[n-1] then pivot lies on right side make l=mid+1
    * else pivot lies on left side including mid element make r=mid
    * now pivot index is = l,
    * do binary search on left subArray ie from 0 to pivot-1 if target found return index else do binary
    search on right part of the subarray
    * in binary search we take nums, l, r, target, we iterate until l<=r
    * find mid=l+(r-l)/2 if element at mid is equal to target then we return mid
    * if element at mid > target then we make r=mid-1 else l=mid+1
    * we return -1 at the end
    time & space:
    * it takes log n time and const space

    Approach 2: using single binary search
    * intuition is to find the mid element and check if it is target if so return mid else check if
    element at mid is >= left element if so then left part is sorted and we do search in this space
    * we check if target is within the bounds of l and mid if so we make r=mid-1 else we make l=mid+1
    * if right part is sorted we check if target is within mid and r if so we make l=mid+1 else r=mid-1
    algo:
    * initialize l=0, r=n-1
    * iterate until l<=r, find mid=l+(r-l)/2;
    * check if element at mid == target if so return mid else
    * check if nums[mid] >= nums[l] if so left part of the array is sorted
        * check if target is >= nums[l] and < nums[mid] if so make r=mid-1 else make l=mid+1
    * else right part of the array is sorted
        * check if target > nums[mid] and < nums[r] if so make l=mid+1 else r = mid-1;
    * return -1 at the end
    time & space:
    * takes log n time and const space
     */

    public int search(int[] nums, int target) {
        return searchA2(nums,target);
    }

    public int searchA2(int[] nums, int target) {
        int l=0,n=nums.length,r=nums.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]>=nums[l]){
                if(target>= nums[l] && target < nums[mid])
                    r=mid-1;
                else l=mid+1;
            }else{
                if(target > nums[mid] && target <= nums[r])
                    l=mid+1;
                else r=mid-1;
            }
        }
        return -1;
    }

    public int searchA1(int[] nums, int target) {
        int l=0,n=nums.length,r=nums.length-1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]>nums[n-1])
                l=mid+1;
            else r=mid;
        }
        int pos = bin(nums,0,l-1,target);
        if(pos!=-1)return pos;
        else return bin(nums,l,n-1,target);
    }

    public int bin(int[] nums, int l, int r, int target){
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]<target)l=mid+1;
            else r=mid-1;
        }
        return -1;
    }


}