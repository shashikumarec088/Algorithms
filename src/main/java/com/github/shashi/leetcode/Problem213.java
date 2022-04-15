package com.github.shashi.leetcode;
import java.util.*;
public class Problem213 {
    Map<Integer,Integer> map = new HashMap<>();
    public int rob(int[] nums) {
        return robA2(nums);
    }

    public int robA1(int[] nums){
        int n = nums.length;
        if(n==1) return nums[0];
        int a1 = rec(nums,n-2,0);
        map.clear();
        return Math.max(a1,rec(nums,n-1,1));
    }

    public int robA2(int[] nums){
        int n = nums.length;
        if(n==1)return nums[0];
        if(n==2)return Math.max(nums[0],nums[1]);
        return Math.max(robA21(nums,0,n-2),robA21(nums,1,n-1));
    }

    public int robA21(int[] nums, int start, int end){
        int t1=0, t2=0;
        for(int i=start; i<=end; i++){
            int temp = t1;
            t1 = Math.max(nums[i]+t2,t1);
            t2 = temp;
        }
        return t1;
    }

    public int rec(int[] nums, int n, int stop){
        if(n==stop)return nums[n];
        if(n-1==stop)return Math.max(nums[n-1],nums[n]);
        if(map.containsKey(n))return map.get(n);
        map.put(n,Math.max(rec(nums,n-1,stop),nums[n]+rec(nums,n-2,stop)));
        return map.get(n);
    }

}