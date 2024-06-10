package com.github.shashi.leetcode;

public class Problem53 {
    /*
    Maximum Subarray
    Given an integer array nums, find the subarray with the largest sum, and return its sum.

    Example 1:
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
    Example 2:
    Input: nums = [1]
    Output: 1
    Explanation: The subarray [1] has the largest sum 1.
    Example 3:
    Input: nums = [5,4,-1,7,8]
    Output: 23
    Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

    Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer
    approach, which is more subtle.

    Approach 1:
    * intuition is to find all subarray sums and update the maxsum, this can be done in n^2 by holding the curSum
    as we expand the end index
    algo:
    * initialize n to array length and max to int min value
    * iterate i from 0 to n, inside loop initialize cur=0 which holds the cumulative sum from index i to j.
    * iterate j=i to n for each iteration add nums[j] to sum and update the max with latest value
    * return max at the end
    time & space:
    * it takes n^2 time and const space, it leads to TLE in leetcode

    Approach 2: Dynamic Programming / kadanes algorithm.
    * intuition is to iterate over the input array and keep track of cumulative sum, if cur element is >
    cumulative sum then make the cur element as cumulative sum and proceed, after  each update of the cumulative
    sum we also update the maxSum.
    algo:
    * initialize n to nums length, maxSum and curSum to nums[0]
    * iterate i=1 to n, for each iteration update curSum to max of curSum+nums[i], nums[i]
    * update the maxSum with max of curSum if it is > maxSum
    * return maxSum at the end
    time & space:
    * it take n time to iterate the array and constant space.

    Approach 3: devide & conquer
    * intuition is to devide the input into 3 parts left, right and middle, and find the maxsum of midle by
    finding the maximum and also finding the maxSum on left and right subarray using recursion and returning
    the max of 3
    algo:
    * call rec with nums, 0, n-1 where n is array length
    * if start>end then return int min as default
    * find mid from start and end
    * initialize leftMax and rightMax to 0 and iterate over array from mid-1 to start find the maxSum
    * similarly do it for the right part of the array find rightMax
    * sum left, right max and mid to get the midMax element
    * recursively find max of left part of array ie leftMax and rightPart of array ie rightMax
    * return the max of leftMax, midMax and rightMax
    time & space:
    * it takes nlogn time n for finding the max of mid element for each recursive call since we devide the
    array into half every time there will be logn calls so total nlogn time complexity and logn space
    *
     */
    public static void main(String[] args) {
        Problem53 problem53 = new Problem53();
        int[] input = {-1,-2};
        problem53.maxSubArray(input);
    }
    public int maxSubArray(int[] nums) {
        return maxSubArrayA3(nums);
    }

    public int maxSubArrayA3(int[] nums) {
        return rec(nums,0,nums.length-1);
    }

    public int rec(int[] nums, int start, int end){
        if(start>end)return Integer.MIN_VALUE;
        int cur=0, leftMax=0,rightMax=0;
        int mid = (start+end)/2;
        for(int i=mid-1;i>=start;i--){
            cur+=nums[i];
            leftMax = Math.max(cur,leftMax);
        }
        cur=0;
        for(int i=mid+1;i<=end;i++){
            cur+=nums[i];
            rightMax = Math.max(cur,rightMax);
        }
        int midMax = leftMax+rightMax+nums[mid];
        leftMax = rec(nums,start,mid-1);
        rightMax=rec(nums,mid+1,end);
        return Math.max(Math.max(leftMax,rightMax),midMax);
    }

    public int maxSubArrayA2(int[] nums) {
        int n=nums.length;
        int maxSum=nums[0], curSum=nums[0];
        for(int i=1; i<n;i++){
            curSum = Math.max(curSum+nums[i],nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    public int maxSubArrayA1(int[] nums) {
        int n=nums.length, max=Integer.MIN_VALUE;
        for(int i=0; i<n;i++){
            int cur=0;
            for(int j=i;j<n;j++){
                cur+=nums[j];
                max=Math.max(cur,max);
            }
        }
        return max;
    }
}