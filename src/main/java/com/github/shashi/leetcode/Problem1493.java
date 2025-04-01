package com.github.shashi.leetcode;

public class Problem1493 {
    /*
    1493. Longest Subarray of 1's After Deleting One Element
    Given a binary array nums, you should delete one element from it.

    Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0
    if there is no such subarray.

    Example 1:

    Input: nums = [1,1,0,1]
    Output: 3
    Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
    Example 2:

    Input: nums = [0,1,1,1,0,1,1,0,1]
    Output: 5
    Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
    Example 3:

    Input: nums = [1,1,1]
    Output: 2
    Explanation: You must delete one element.

    Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.

    Approach 1: 2 pointer with counting zeros
    * intuition is to iterate over array and count zeros when count > 1 shrink the window
    algo:
    * init i=0,zeros=0,max=0,n=length
    * iterate j = 0 to <n
        * if nums[j]==0 inc zeros
        * while zeros > 1
            * if nums[i]==0 dec zeros
            * inc i
        * update max = max(max,j-i)(here since we are subtr indexes count will be less by 1)
    * return max;
    time & space:
    * it takes n time and n space

     */

    public int longestSubarray(int[] nums) {
        return longestSubarrayA1(nums);
    }

    public int longestSubarrayA1(int[] nums) {
        int i=0,max=0,zeros=0,n=nums.length;
        for(int j=0;j<n;j++){
            zeros+= nums[j]==0?1:0;
            while(zeros>1){
                if(nums[i]==0)zeros--;
                i++;
            }
            max = Math.max(max,j-i);
        }
        return max;
    }
}
