package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem1051 {

    public int heightChecker(int[] heights) {
        return heightCheckerA2(heights);
    }

    public int heightCheckerA2(int[] nums){
        int[] counts = new int[101];
        for(int num: nums)counts[num]++;
        int cur=0, res=0;;

        for(int i=0; i<nums.length; i++){
            while(counts[cur]==0)cur++;
            if(cur!=nums[i])res++;
            counts[cur]--;
        }
        return res;
    }
    public int heightCheckerA1(int[] heights) {
        int[] exp = Arrays.copyOf(heights,heights.length);
        Arrays.sort(exp);
        int ans=0;
        for(int i=0; i<exp.length; i++){
            if(heights[i]!=exp[i]) ans++;
        }
        return ans;
    }
}