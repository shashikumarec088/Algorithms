package com.github.shashi.leetcode;

public class Problem918 {
    /*
    Maximum Sum Circular Subarray
    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
    A circular array means the end of the array connects to the beginning of the array. Formally, the next element of
    nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
    A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i],
    nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

    Example 1:
    Input: nums = [1,-2,3,-2]
    Output: 3
    Explanation: Subarray [3] has maximum sum 3.
    Example 2:
    Input: nums = [5,-3,5]
    Output: 10
    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
    Example 3:
    Input: nums = [-3,-2,-3]
    Output: -2
    Explanation: Subarray [-2] has maximum sum -2.
    Constraints:
    n == nums.length
    1 <= n <= 3 * 104
    -3 * 104 <= nums[i] <= 3 * 104

    Approach 1: kadanes algorith
    * intuition is to find the maxSum of the array using the kadanses algo and for rotated array using the
    prefix and suffix maxSums and return the max of normal array maxSum and rotated array maxSum
    algo:
    * to find the maxSum in the rorated part of the array we need to find the maxSuffix sum in the right
    part of the array  and then we need to find the prefix sum then update rotate max with max of the
    existing rotate max and the sum of prefixSum and suffixSum
    * initialize n to array length, rightMax of type int array of size n.
    * rightMax will hold the running sum from right to left og array
    * make rightMax[n-1] will be nums[n-1], rightSum = nums[n-1]
    * iterate from i=n-2 to 0 and for each iteration add nums[i] to rightSum
    * make rightMax[i] as max of rightSum and the value at rightMax[i+1]
    * to find rotateMax which holds the max sum in rotated array, initialize rorateMax to nums[0]
    * initialize prefixSum=0 (here it is different for normal sum where we init to nums[0] and iterate from 1)
    * iterate i from 0 to n-2 here end bound is n-1 we need to consider only rotated arrays
    * for each iteration prefixSum will hold the running sum, update rotate max with prefixSum+ rightMax[i+1]
    which has the max running sum from end of the array till i+1 th element if this value is > existing rotateMax
    value.
    * compute the maxSum for normal array using the kadanes algorithm where we initialize curSum=[0], maxSum=nums[0]
    * iterate from i=1 to n-1 and for each iteration we update curSum with max of nums[i] and nums[i]+curSum and
    maxSum with max of maxSum and curSum
    * return max of maxSum and rotateMax at the end
    time & space:
    * it takes n time and constant space

    Appraoch 2: using minimum sum subarray
    * intuition is that instead of thinking maxSum in rorated array as sum of prefixMax and suffixMax, we can think
    it as sum of all elements in array minus the subarray in the middle of array, aim is to find the minimum sum
    subarray in the middle. this can be done using kadenes algorithm. we find the total sum, sumMin and sumMax then
    we can subtract sumMin from total to get the maxSum in rotated array. we need to consider case when all the elements
    negative in which case we have total and sumMin will be same during which maximum sum will be sumMax.
    algo:
    * init curMax,sumMax,curMin,sumMin,total to nums[0]
    * iterate i from 1 to n for each iteration
    * calculate curMax will be max of nums[i], nums[i]+curMax
    * sumMax will  be max of sumMax and curMax
    * curMin will be min of nums[i] and nums[i]+curMin
    * sumMin will be min of sumMin and curMin
    * total will be total plus nums[i]
    * at the end we check for the case when summin==total then we return sumMax
    * we return max of sumMax, total-sumMin
    time & space:
    * it takes n time and constant space
     */

    public int maxSubarraySumCircular(int[] nums) {
        return maxSubarraySumCircularA2(nums);
    }

    public int maxSubarraySumCircularA2(int[] nums) {
        int n = nums.length;
        int curMax = nums[0];
        int sumMax = nums[0];
        int curMin = nums[0];
        int sumMin = nums[0];
        int total = nums[0];
        for(int i=1; i<n;i++){
            curMax = Math.max(nums[i],nums[i]+curMax);
            sumMax = Math.max(curMax,sumMax);
            curMin = Math.min(nums[i],nums[i]+curMin);
            sumMin = Math.min(curMin,sumMin);
            total += nums[i];
        }
        if(total==sumMin)return sumMax;
        return Math.max(sumMax,total-sumMin);
    }

    public int maxSubarraySumCircularA1(int[] nums) {
        int n = nums.length;
        int[] rightMax = new int[n];
        int rightSum = nums[n-1];
        rightMax[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--){
            rightSum += nums[i];
            rightMax[i] = Math.max(rightSum,rightMax[i+1]);
        }
        int rotateMax = nums[0];
        int prefixSum=0;
        for(int i=0; i<n-1; i++){
            prefixSum += nums[i];
            rotateMax = Math.max(rotateMax,prefixSum+rightMax[i+1]);
        }
        int curSum=nums[0];
        int maxSum=nums[0];
        for(int i=1; i<n;i++){
            curSum =Math.max(nums[i],curSum+nums[i]);
            maxSum = Math.max(maxSum,curSum);
        }
        return Math.max(maxSum,rotateMax);
    }
}
