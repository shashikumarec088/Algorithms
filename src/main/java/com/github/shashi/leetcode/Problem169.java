package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem169 {
    public int majorityElement(int[] nums) {
        return majorityElementA4(nums);
    }

    /*
    intuition behind the approach is based on the boyer-moore
    algorithm, this algo states to keep counting the number
    of times the number occurred and discounting the mismatched
    number when count is zero update the candidate, this will
    work because major numbor appears more than half
    */
    public int majorityElementA4(int[] nums) {
        int count=0;
        int candidate=0;
        for(int num : nums){
            if(count==0){
                candidate=num;
            }
            count += candidate==num?+1:-1;
        }
        return candidate;
    }

    public int majorityElementA3(int[] nums) {
        int n = nums.length;
        int major = 0;
        for(int i=0; i<32;i++){
            int bit=1<<i;
            int cn=0;
            for(int num:nums)
                if((num & bit) !=0)
                    cn++;
            if(cn>n/2)
                major = major | bit;
        }
        return major;
    }

    public int majorityElementA2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public int majorityElementA1(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int major = nums[0];
        for(int i=0; i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            if(map.get(nums[i])>map.get(major))
                major=nums[i];
        }
        return major;
    }
}