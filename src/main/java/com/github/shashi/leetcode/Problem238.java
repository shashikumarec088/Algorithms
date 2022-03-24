package com.github.shashi.leetcode;

public class Problem238 {
    public int[] productExceptSelf(int[] nums) {
        return productExceptSelfA2(nums);
    }

    public int[] productExceptSelfA2(int[] nums){
        int n = nums.length, cur=1;
        int[] ans = new int[n];
        for(int i=0;i<n; i++){
            cur = nums[i]*cur;
            ans[i]=cur;
        }
        cur=1;
        for(int i=n-1; i>=0; i--){
            if(i!=0)ans[i]=ans[i-1]*cur;
            else ans[i]=cur;
            cur *=nums[i];
        }
        return ans;
    }

    public int[] productExceptSelfA1(int[] nums){
        int n = nums.length;
        int[] forward = new int[n];
        int[] backward = new int[n];
        int[] ans = new int[n];
        int cur= 1;
        for(int i=0; i<n; i++){
            cur *= nums[i];
            forward[i]=cur;
        }
        cur=1;
        for(int i=n-1; i>=0; i--){
            cur *= nums[i];
            backward[i]=cur;
        }

        for(int i=0; i<n; i++){
            if(i==0)ans[i]=backward[i+1];
            else if(i==n-1)ans[i] = forward[n-2];
            else ans[i] = forward[i-1]*backward[i+1];
        }
        return ans;
    }
}