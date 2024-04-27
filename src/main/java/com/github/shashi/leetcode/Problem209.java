package com.github.shashi.leetcode;

public class Problem209 {
    /*

    209. Minimum Size Subarray Sum

    Given an array of positive integers nums and a positive integer target, return the minimal
    length of a subarray whose sum is greater than or equal to target. If there is no such
    subarray, return 0 instead.

    Example 1:

    Input: target = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    Example 2:

    Input: target = 4, nums = [1,4,4]
    Output: 1

    Approach 1:
      * intuition is to use the 2 pointers 1 slow and 1 fast pointer, keep adding the
      elements to sum and keep incrementing the fast pointer
      algo:
      * initialize i=0,j=0,minLen=infi sum=0, start iterating from j=0
      * add element to sum iterate inner loop until sum >= target and keep updating the minLen by inc i
      * when sum below target inc j and repeat
      * at the end check if minLen is infi if so return 0 else minLen
      time & space:
      time is n and we use const space

    Approach 2:
      * intuition is to try all the sub array combinations and keep updating the lengths
      whenever you find the small length which satifies the condition
      algo:
      * to find the sum of sub arrays we can have the extra array which holds the sum till
      that position and use this array to find the sub array sum on o(1)
      time sum(i,j) = sum[j]-sum[i]+nums[i]
      * start iterating from i=0 for each value of i start iterating from j=i to to n
      until the sum is greater than target then update the length and break the inner loop
      time & space:
      time is n2 and space is n

      Approach 3:

      * intuition is to use the binary search on top of the sum array from approch 2 instead of
      iterating from position i to end so that the overall time complexity can be reduced from
      n2 to nlogn
      algo:
      * initialize the extra sum array and calculate sum till that point of i for all values of i
      *iterate from i=0 to n, for each value of i do the binary search to find the end position
      * in binary search we calculate the mid=(e-s)/2+s and sum till mid from i
      which is sum = sums[mid]-sums[i]+nums[i]
      * check if sum is < target if so make s=mid+1 else e=mid and repeat till we find the boundaries
      * at the end of binary search remember to check if sum >= target is so update minLen and
      repeat the search for other values of i
      time & space:
      time is nlogn and space is n


     */

    public static void main(String[] args) {
        Problem209 problem209 = new Problem209();
        int[] nums = {2,3,1,2,4,3};
        problem209.minSubArrayLen( 7,nums);
    }
    public int minSubArrayLen(int target, int[] nums) {
        return minSubArrayLenA2(target, nums);
    }

    public int minSubArrayLenA3(int target, int[] nums) {
        int n = nums.length;
        int sum =0, minLen = Integer.MAX_VALUE;
        int[] sumAll = new int[n];
        sumAll[0] = nums[0];
        for(int i=1; i<n; i++)sumAll[i] = sumAll[i-1]+nums[i];
        for(int i=0; i<n; i++){
            int  s= i, e = n-1;
            while(s<e){
                int mid = s +(e-s)/2;
                int sumCur = sumAll[mid] - sumAll[i]+nums[i];
                if(sumCur < target){
                    s = mid+1;
                }else e = mid;
            }
            if((sumAll[e] - sumAll[i]+nums[i]) >= target)
                minLen = Math.min(minLen, s-i+1);
        }
        return minLen==Integer.MAX_VALUE?0:minLen ;
    }

    /*
   bf optimized by keeping the sum in the extra array so o(n2)
   is achieved
    */
    public int minSubArrayLenA2(int target, int[] nums) {
        int n = nums.length;
        int sum =0, minLen = Integer.MAX_VALUE;
        int[] sumAll = new int[n];
        sumAll[0] = nums[0];
        for(int i=1; i<n; i++)sumAll[i] = sumAll[i-1]+nums[i];
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                sum = sumAll[j] - sumAll[i]+nums[i];
                if(sum >= target){
                    minLen = Math.min(minLen, j-i+1);
                    break;
                }
            }
        }
        return minLen==Integer.MAX_VALUE?0:minLen ;
    }


    public int minSubArrayLenA1(int target, int[] nums) {
        int i=0,j=0,sum=0,minLen=Integer.MAX_VALUE,n=nums.length;
        while(j<n){
            sum+=nums[j];
            while(sum>=target){
                minLen = Math.min(j-i+1,minLen);
                sum -= nums[i++];
            }
            j++;
        }
        return minLen==Integer.MAX_VALUE?0:minLen;
    }
}