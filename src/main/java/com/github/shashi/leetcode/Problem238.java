package com.github.shashi.leetcode;

public class Problem238 {
    /*
    Product of Array Except Self
    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
    of nums except nums[i].

    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

    You must write an algorithm that runs in O(n) time and without using the division operation.

    Example 1:
    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]
    Example 2:
    Input: nums = [-1,1,0,-3,3]
    Output: [0,0,9,0,0]
    Constraints:
    2 <= nums.length <= 105
    -30 <= nums[i] <= 30
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

    Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as
    extra space for space complexity analysis.)

    Approach 1:
    * intuition is to first define the ans array and calculate each position product excluding that element
    and from end using the input build the product exluding that element and multiply with element at that
    position in the answer array
    algo:
    * create ans array of size n, define int variable prod=1;
    * initialize 0th element in ans as 1
    * iterate from i=1 to n and compute ans[i] = ans[i-1]*nums[i-1]
    * then iterate from i=n-1 to n and compute ans[i] = ans[i] * prod and update prod = prod * nums[i];
    * return ans at end
    time & space:
    * time is o(n) and space is const except ans
     */
    public int[] productExceptSelf(int[] nums) {
        return productExceptSelfA1(nums);
    }

    public int[] productExceptSelfA1(int[] nums) {
        int n = nums.length,prod=1;
        int[] ans = new int[n];
        ans[0] = 1;
        for(int i=1; i<n;i++)ans[i] = ans[i-1]*nums[i-1];
        for(int i=n-1; i>=0; i--){
            ans[i] = ans[i]*prod;
            prod = prod*nums[i];
        }
        return ans;
    }
}