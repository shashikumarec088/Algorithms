package com.github.shashi.leetcode;

public class Problem55 {
    public boolean canJump(int[] nums) {
        return canJumpA3(nums);
    }

    /*
    this is greedy approach we start from end and track the min
    reachable index until 0 if we reach 0 then we will be
    able to reach end, time complexity is O(n) and no extra space is
    needed
    */
    public boolean canJumpA3(int[] nums) {
        int n = nums.length;
        int li=n-1;
        for(int i=n-2;i>=0;i--){
            if(i+nums[i]>=li)
                li=i;
        }
        return li==0;
    }

    /*
        this is dp problem using bottom up, we start from end and
        try to reach the 0th position, since we can reach to end when
        i=n-1 we default it to true and iterate from n-2 to 0 for
        each value we try all the combinations,
        this is O(n2) and space is O(n)
    */
    public boolean canJumpA2(int[] nums) {
        int n = nums.length;
        Boolean [] cache = new Boolean[n];
        cache[n-1]=true;
        for(int i=n-2; i>=0; i--){
            int max = Math.min(nums[i]+i,n-1);
            for(int j=max;j>i;j--){
                if(cache[j]!= null && cache[j] ==true){
                    cache[i]=true;
                    break;
                }
            }
        }
        return cache[0]!= null && cache[0] ==true;
    }

    public boolean canJumpA1(int[] nums) {

        Boolean [] cache = new Boolean[nums.length];
        return rec(nums,0,cache);
    }

    /*
    intuition behind the below approach is to reach the last position
    by considering all the possible moments from current index,
    so it is a backtracking problem we see that there are repeatative
    calculations so we use memoization
    */
    public boolean rec(int[] nums, int i, Boolean[] cache){
        if(i >= nums.length-1)return true;
        if(nums[i]==0)return false;
        if(cache[i]!=null)return cache[i];
        for(int j=nums[i]; j>=1; j--){
            boolean res = rec(nums,i+j,cache);
            if(i+j < nums.length){
                cache[i+j] = res;
            }
            if(res)return true;
        }
        cache[i]=false;
        return false;
    }
}