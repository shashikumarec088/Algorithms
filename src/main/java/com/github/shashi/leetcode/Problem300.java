package com.github.shashi.leetcode;
import java.util.*;
public class Problem300 {
    public int lengthOfLIS(int[] nums) {
        return lengthOfLISA3(nums);
    }

    public int lengthOfLISA3(int[] nums){
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<n; i++){
            int last = list.get(list.size()-1);
            if(nums[i]<= last){
                list.set(binSearch(list,nums[i]),nums[i]);
            }else list.add(nums[i]);
        }
        return list.size();
    }

    public int binSearch(List<Integer> list, int value){
        int l=0, r = list.size()-1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(list.get(mid)<value)
                l=mid+1;
            else r = mid;
        }
        return l;
    }

    public int lengthOfLISA2(int[] nums){
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<n; i++){
            int last = list.get(list.size()-1);
            if(nums[i]<= last){
                int j = 0;
                while(list.get(j)<nums[i])
                    j++;
                list.set(j,nums[i]);
            }else list.add(nums[i]);
        }
        return list.size();
    }

    public int lengthOfLISA1(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0]=1;
        int max = dp[0];
        for(int i=1; i<n; i++){
            dp[i]=1;
            for(int j = 0; j<i;j++)
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
