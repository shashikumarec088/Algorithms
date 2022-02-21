package com.github.shashi.leetcode;
import java.util.*;
public class Problem740 {
    public int deleteAndEarn(int[] nums) {
        return deleteAndEarnA2(nums);
    }

    public int deleteAndEarnA2(int[] nums){
        int[] points = new int[10001];
        for(int num : nums)
            points[num] += num;
        return houseRob(points);
    }

    public int houseRob(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i=2; i<n; i++)
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        return dp[n-1];
    }

    public int deleteAndEarnA1(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            int count = map.getOrDefault(num,0);
            map.put(num,count+1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        int prev=0, prev2 =0;
        for(int i=0; i<list.size(); i++){
            int curEarn = list.get(i)*map.get(list.get(i));
            if(i>0 && list.get(i)==list.get(i-1)+1){
                int temp = prev;
                prev = Math.max(prev, prev2+curEarn);
                prev2 = temp;
            }
            else{
                int temp = prev;
                prev = prev + curEarn;
                prev2 = temp;
            }
        }
        return prev;
    }
}