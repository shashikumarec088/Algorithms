package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem228 {
    /*
    Summary Ranges
    You are given a sorted unique integer array nums.
    A range [a,b] is the set of all integers from a to b (inclusive).
    Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
    That is, each element of nums is covered by exactly one of the ranges, and there is no integer x
    such that x is in one of the ranges but not in nums.

    Each range [a,b] in the list should be output as:
    "a->b" if a != b
    "a" if a == b
    Example 1:
    Input: nums = [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: The ranges are:
    [0,2] --> "0->2"
    [4,5] --> "4->5"
    [7,7] --> "7"
    Example 2:
    Input: nums = [0,2,3,4,6,8,9]
    Output: ["0","2->4","6","8->9"]
    Explanation: The ranges are:
    [0,0] --> "0"
    [2,4] --> "2->4"
    [6,6] --> "6"
    [8,9] --> "8->9"
    Constraints:
    0 <= nums.length <= 20
    -231 <= nums[i] <= 231 - 1
    All the values of nums are unique.
    nums is sorted in ascending order.

    approach 1:
    * intuition is to iterate with 2 pointers, whenever the sequence breaks add the current sequence to the list and
    continue with next sequence
    algo:
    * initialize i=0, empty list to capture the results
    * iterate from j=1 to n
    check if current element is prev+1 if not create the range by calling the method getRange with i, j-1 (we consider j-1
    because sequence breaks at j so we should not consider j)
    * getRange take nums, i , j it creates empty string builder appends i
    * if i != j then appends ->j and returns as string
    * once the range is added reset i=j and continue
    * remember to add the last range at the end by calling getRange with nums, i and j-1
    * return the list
    time & space:
    time is n and space is const only used for ans which is in worst case n
     */

    public List<String> summaryRanges(int[] nums) {
        return summaryRangesA1(nums);
    }

    public List<String> summaryRangesA1(int[] nums) {
        List<String> list = new ArrayList<>();
        if(nums.length==0)return list;
        int i=0,n=nums.length;
        for(int j=1; j<n;j++){
            if(nums[j-1]+1 != nums[j]){
                list.add(getRange(nums,i,j-1));
                i=j;
            }
        }
        list.add(getRange(nums,i,n-1));
        return list;
    }
    private String getRange(int[] nums, int i, int j){
        StringBuilder sb = new StringBuilder();
        sb.append(nums[i]);
        if(i != j){
            sb.append("->");
            sb.append(nums[j]);
        }
        return sb.toString();
    }
}
