package com.github.shashi.leetcode;

public class Problem152 {
    public int maxProduct(int[] nums) {
        return maxProductA3(nums);
    }

    public int maxProductA3(int[] nums){
        int ans=Integer.MIN_VALUE, cur=1,n=nums.length;
        for(int i=0; i<n; i++){
            cur *= nums[i];
            ans=Math.max(ans,cur);
            if(cur==0)cur=1;
        }
        cur=1;
        for(int i=n-1;i>=0;i--){
            cur*=nums[i];
            ans = Math.max(ans,cur);
            if(cur==0)cur=1;
        }
        return ans;
    }

    public int maxProductA2(int[] nums){
        int ans=Integer.MIN_VALUE, n = nums.length;
        for(int i=0; i<n; i++){
            int cur=1;
            for(int j=i; j<n; j++){
                cur *= nums[j];
                ans= Math.max(cur,ans);
            }
        }
        return ans;
    }

    public int maxProductA1(int[] nums){
        int min=nums[0],max=nums[0],p=nums[0];
        for(int i=1; i<nums.length; i++){
            int minTemp = Math.min(nums[i],Math.min(min*nums[i],max*nums[i]));
            max = Math.max(nums[i],Math.max(min*nums[i],max*nums[i]));
            min = minTemp;
            p = Math.max(p,max);

        }
        return p;
    }
}