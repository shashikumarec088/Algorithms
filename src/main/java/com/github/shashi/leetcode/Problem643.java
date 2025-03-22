package com.github.shashi.leetcode;

public class Problem643 {
    /*
    643. Maximum Average Subarray I

    You are given an integer array nums consisting of n elements, and an integer k.

    Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
    Any answer with a calculation error less than 10-5 will be accepted.

    Example 1:

    Input: nums = [1,12,-5,-6,50,3], k = 4
    Output: 12.75000
    Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
    Example 2:

    Input: nums = [5], k = 1
    Output: 5.00000

    Constraints:

    n == nums.length
    1 <= k <= n <= 105
    -104 <= nums[i] <= 104

    Approach 1: using sum array
    * intuition is to store the sum of elements till i in array then iterate from k to n and find the
    max avg sum
    algo:
    * init n is array length, sums array of size n
    * make sums[0] = nums[0]
    * iterate from i = 1 to n
        * sums[i] = nums[i] + sums[i-1]
    * init res = sums[0]
    * iterate i=k to n
        * cur = sums[i] - sums[i-k] + nums[i-k]
        * res = max(res,cur)
    * return res
    time & space:
    * it takes n space and n time

    approach 2: without extra array
    * intuition is same as approach 1, first we can store the sum of k elements then iterate from
    i=k to n and at each step compute the avg by adding the current element and subtracting the i-k element
    algo:
    * init sum=0, n is nums length
    * iterate i=0 to <k
        * sum+= nums[i]
    * max = sum * 1.0/k
    * iterate i=k to i<n
        * sum = sum + nums[i] - nums[i-k]
        * max = max(max,snum*1.0/k)
    * return max at the end
     */

    public double findMaxAverage(int[] nums, int k) {
        return findMaxAverageA1(nums,k);
    }

    public double findMaxAverageA2(int[] nums, int k) {
        int n = nums.length;
        double sum = 0;
        for(int i=0; i<k;i++)
            sum+= nums[i];
        double max = sum*1.0/k;
        for(int i=k; i<n;i++){
            sum = sum+nums[i]-nums[i-k] ;
            max = Math.max(max,sum*1.0/k);
        }
        return max;
    }


    public double findMaxAverageA1(int[] nums, int k) {
        int n = nums.length;
        double[] sums = new double[n];
        sums[0] = nums[0];
        for(int i=1; i<n;i++)
            sums[i] = sums[i-1]+nums[i];
        double max = sums[k-1]*1.0/k;
        for(int i=k; i<n;i++){
            double cur = sums[i] - sums[i-k] ;
            max = Math.max(max,cur*1.0/k);
        }
        return max;
    }
}
