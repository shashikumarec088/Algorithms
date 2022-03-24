package com.github.shashi.leetcode;
import java.util.*;
public class Problem217 {
    public boolean containsDuplicate(int[] nums) {
        return containsDuplicateA1(nums);
    }

    public boolean containsDuplicateA1(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num))return true;
            set.add(num);
        }
        return false;
    }
}