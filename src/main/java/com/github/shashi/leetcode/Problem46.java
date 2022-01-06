package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.*;

public class Problem46 {
    public static void main(String[] args) {
        Problem46 problem119 = new Problem46();
        System.out.println(problem119.permute(new int[]{1,2,3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        return permuteBack2(nums);
    }

    public List<List<Integer>> permuteBack2(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int element : nums)list.add(element);
        backTrack2(result,list,0);
        return result;
    }

    public void backTrack2(List<List<Integer>> result,List<Integer> list,
                           int start){
        if(start == list.size())
            result.add(new ArrayList<>(list));
        else{
            for(int i=start; i<list.size();i++){
                Collections.swap(list,start,i);
                backTrack2(result,list,start+1);
                Collections.swap(list,start,i);
            }
        }
    }

    public List<List<Integer>> permuteBack(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result,new ArrayList<>(),nums);
        return result;
    }

    public void backTrack(List<List<Integer>> result, List<Integer> temp, int[] nums){
        if(temp.size()==nums.length)
            result.add(new ArrayList<>(temp));
        else{
            for(int i=0; i<nums.length;i++){
                if(temp.contains(nums[i]))continue;
                temp.add(nums[i]);
                backTrack(result,temp,nums);
                temp.remove(temp.size()-1);
            }
        }
    }
}
