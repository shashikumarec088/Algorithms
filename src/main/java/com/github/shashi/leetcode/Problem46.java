package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.*;

public class Problem46 {
    /*
    Permutations
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any
    order.
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    Example 2:
    Input: nums = [0,1]
    Output: [[0,1],[1,0]]
    Example 3:
    Input: nums = [1]
    Output: [[1]]
    Constraints:
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    All the integers of nums are unique.

    Approach 1: backtracking
    * intuition is to keep adding the element to cur list until element not in list and add to result when list size
    equal to nums size
    algo:
    * create list of list of integers result and call rec with empty list and nums
    * in rec if list size is equal to nums then add to res and return
    * iterate over all nums and if num is not in cur list then add num to list and call rec
    * return result at the end
    time & space:
    * there will be n! permutations and at each step we check if num contains which take n and also to add result
    it takes n time. total n * n! time and takes n time for recursion stack.
     */
    public List<List<Integer>> permute(int[] nums) {
        return permuteA1(nums);
    }

    public List<List<Integer>> permuteA1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        rec(new ArrayList<>(),nums,res);
        return res;
    }

    public void rec(List<Integer> set, int[] nums, List<List<Integer>> res){
        if(set.size()==nums.length){
            res.add(new ArrayList<>(set));
            return;
        }
        for(int num: nums){
            if(!set.contains(num)){
                set.add(num);
                rec(set,nums,res);
                set.remove(set.size()-1);
            }
        }
    }
}
