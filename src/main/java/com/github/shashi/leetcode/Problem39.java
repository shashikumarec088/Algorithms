package com.github.shashi.leetcode;

import java.util.*;

public class Problem39 {
    /*
    Combination Sum
    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations
    of candidates where the chosen numbers sum to target. You may return the combinations in any order.
    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
    frequency of at least one of the chosen numbers is different.
    The test cases are generated such that the number of unique combinations that sum up to target is less than 150
    combinations for the given input.
    Example 1:
    Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]
    Explanation:
    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
    7 is a candidate, and 7 = 7.
    These are the only two combinations.
    Example 2:
    Input: candidates = [2,3,5], target = 8
    Output: [[2,2,2,2],[2,3,3],[3,5]]
    Example 3:
    Input: candidates = [2], target = 1
    Output: []
    Constraints:
    1 <= candidates.length <= 30
    2 <= candidates[i] <= 40
    All elements of candidates are distinct.
    1 <= target <= 40

    Approach 1: backtracking with start index
    * intuition is to start from first element in nums and iterating over all the elements and check if the sum is
    reaching target if so then add it to result. it is kind of tree traversal and backtracking and exploring the
    remaining parts
    algo:
    * create the res variable of type list of list of integers, call rec with empty list, res,nums,trg and start as 0
    * if trg is 0 then add cur to res and return
    * iterate from i=start to nums length add nums[i] to cur and call rec
    * remove last element from cur and proceed to next iteration.
    * return res at the end
    time & space:
    * takes N^(trg/m) where m is the smallest number in nums of size N, max depth will be trg/m. space is N for
    recursion tree.

    Approach 2: backtracking with set to avoid duplicates
    * it does what is asked in the question, to avoid duplicates we use the set of lists and before adding the
    ans list to res we sort it and add. this is bf approach

     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSumA1(candidates,target);
    }

    public List<List<Integer>> combinationSumA1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        rec(candidates,target,res,new ArrayList<>(),0);
        return res;
    }

    public void rec(int[] nums, int trg,List<List<Integer>> res,List<Integer> list,
                    int start){
        if(trg==0){
            res.add(new ArrayList<>(list));
            return;
        }
        if(trg<0)return;
        for(int i=start; i<nums.length;i++){
            list.add(nums[i]);
            rec(nums,trg-nums[i],res,list,i);
            list.remove(list.size()-1);
        }

    }

    public List<List<Integer>> combinationSumA2(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        rec2(nums,target,res,new ArrayList<>());
        return new ArrayList<>(res);
    }

    public void rec2(int[] nums, int target,Set<List<Integer>> res,List<Integer> list){
        if(target<0)return;
        if(target==0){
            List<Integer> l1 = new ArrayList<>(list);
            Collections.sort(l1);
            res.add(l1);
            return;
        }
        for(int num: nums){
            list.add(num);
            rec2(nums,target-num,res,list);
            list.remove(list.size()-1);
        }
    }
}
