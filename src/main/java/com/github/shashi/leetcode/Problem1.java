package com.github.shashi.leetcode;
import java.util.*;
public class Problem1 {
    public int[] twoSum(int[] nums, int target) {
        return twoSumMap(nums,target);
    }

    public int[] twoSumMap(int[] nums, int target){
        int n = nums.length;
        int[] out = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                out[1]=i;
                out[0]= map.get(diff);
            }
            map.put(nums[i],i);
        }
        return out;
    }
}
