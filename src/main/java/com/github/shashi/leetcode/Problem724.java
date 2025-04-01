package com.github.shashi.leetcode;

public class Problem724 {
    /*
    724. Find Pivot Index

    Given an array of integers nums, calculate the pivot index of this array.

    The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to
    the sum of all the numbers strictly to the index's right.

    If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
    This also applies to the right edge of the array.

    Return the leftmost pivot index. If no such index exists, return -1.

    Example 1:

    Input: nums = [1,7,3,6,5,6]
    Output: 3
    Explanation:
    The pivot index is 3.
    Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
    Right sum = nums[4] + nums[5] = 5 + 6 = 11
    Example 2:

    Input: nums = [1,2,3]
    Output: -1
    Explanation:
    There is no index that satisfies the conditions in the problem statement.
    Example 3:

    Input: nums = [2,1,-1]
    Output: 0
    Explanation:
    The pivot index is 0.
    Left sum = 0 (no elements to the left of index 0)
    Right sum = nums[1] + nums[2] = 1 + -1 = 0


    Constraints:

    1 <= nums.length <= 104
    -1000 <= nums[i] <= 1000

    Approach 1: with extra sums array
    * intuition is to complete the sum till point from right side, then find left sum and compare
    algo:
    * init int[] sums array of size n, lsum=0
    * sums[n-1] = nums[n-1]
    * iterate from i=n-2 to 0
        * sums[i] = sums[i+1] + nums[i]
    * iterate from i=0 <n
        * lsum += nums[i]
        * if(lsum == sums[i])
            return i
    * return -1

    time & space:
    * it takes n time and n space

    Approach 2: constant space
    * intuition is to compute total sum, then compute sum till prev element and compare by subtracting it
    with total sum and current element to get the right sum
    algo:
    * init sum=0, n = elements, lsum=0
    * iterate over nums
        * sum+=nums[i]
    * iterate over nums
        * check if lsum == sum-lsum-nums[i]
            * return i
        * lsum+= nums[i]
    * return -1;

    time & space:
    * constant space and n time

     */
    public int pivotIndex(int[] nums) {
        return pivotIndexA2(nums);
    }

    public int pivotIndexA2(int[] nums){
        int sum=0, leftSum=0;
        for(int num : nums)sum+=num;
        for(int i=0; i<nums.length;i++){
            if(leftSum == (sum-leftSum - nums[i])) return i;
            leftSum += nums[i];
        }
        return -1;
    }

    public int pivotIndexA1(int[] nums) {
        int n = nums.length;
        int[] rsums = new int[n];
        rsums[n-1] = nums[n-1];
        for(int i=nums.length-2; i>=0; i--){
            rsums[i] = nums[i]+rsums[i+1];
        }
        int ls=0;
        for(int i=0; i<n;i++){
            ls+= nums[i];
            if(ls== rsums[i])return i;
        }
        return -1;
    }
}
