package com.github.shashi.leetcode;
import java.util.*;
public class Problem128 {
    /*
    Longest Consecutive Sequence
    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    You must write an algorithm that runs in O(n) time.

    Example 1:
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
    Example 2:
    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9

    Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109

    Approach 1:
    * intuition is to keep the numbers in set and iterate over each number and check if next number in set
    and update the maxlength until we have the next number repeat this process for all the numbers in list
    algo:
    * create the set, iterate from first element to last
    * for each element iterate until cur+1 is present in set and keep updating the length
    * return the length at the end
    time & space:
    * n space and n^2 time

    Approach 2:
    * intuition is to sort the array and checking the longest sequence and updating the length
    algo:
    * if the length of array is < 2 return length, else have lenth as 1 and start the iteration from j=1
    on sorted array,
    * if current element is prev +1 then inc cur length else if same as prev then continue to next element without
    increasing the array length, else update the maxlength and reset the curlength to 1 and continue
    * remember to update the maxLength at the end to handle the case for all numbers in array are seq
    * then return the maxLength
    time & space:
    * time is nlogn and space is nlogn for sorting

    Approach 3:
    * intuition is to use set and search for consecutive elements only if num-1 does not exists in set. this
    makes sure that we iterate once per sequence and skip iteration for all elements in the sequence
    algo:
    * create the set add all elements, iterate for each element in array
    * if cur-1 is not in set then iterate for the sequence and update the length at the end
    * return the length at the end
    time & space:
    * time is n and space is n
     */

    public int longestConsecutive(int[] nums) {
        return longestConsecutiveA1(nums);
    }


    public int longestConsecutiveA3(int[] nums) {
        if(nums.length<2)return nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num : nums)set.add(num);
        int n=nums.length, maxLen =1;
        for(int num : nums){
            if(!set.contains(num-1)){
                int curLen =1;
                while(set.contains(num+1)){
                    curLen++;
                    num++;
                }
                maxLen = Math.max(curLen,maxLen);
            }
        }
        return maxLen;
    }


    public int longestConsecutiveA2(int[] nums) {
        if(nums.length<2)return nums.length;
        Arrays.sort(nums);
        int n=nums.length, maxLen =0, curLen=1;
        for(int j=1;j<n;j++){
            if(nums[j]==nums[j-1]+1){
                curLen++;
            }else if(nums[j]!=nums[j-1]){
                maxLen = Math.max(maxLen,curLen);
                curLen=1;
            }
        }
        return Math.max(maxLen,curLen);
    }


    public int longestConsecutiveA1(int[] nums) {
        int n=nums.length, maxLen=0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums)set.add(num);
        for(int num : nums){
            int curMax=1;
            while(set.contains(num+1)){
                curMax++;
                num++;
            }
            maxLen = Math.max(curMax,maxLen);
        }
        return maxLen;
    }
}
