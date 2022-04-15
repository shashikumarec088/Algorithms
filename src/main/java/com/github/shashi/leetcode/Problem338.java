package com.github.shashi.leetcode;

public class Problem338 {
    public int[] countBits(int n){
        return countBitsA3(n);
    }

    public int[] countBitsA3(int n){
        int[] res = new int[n+1];
        for(int i=1; i<=n; i++){
            res[i] = solve(i,res);
        }
        return res;
    }

    public int solve(int n, int[] res){
        if(n == 0|| n==1)return n;
        if(res[n]!=0)return res[n];
        if(n%2==0)
            res[n]=solve(n/2,res);
        else res[n]=1+solve(n/2,res);
        return res[n];
    }
    public int[] countBitsA2(int n){
        int[] ans = new int[n+1];
        int offSet=1;
        for(int i=1; i<=n; i++){
            if(2*offSet==i)
                offSet*=2;
            ans[i] = 1+ans[i-offSet];
        }
        return ans;
    }
    public int[] countBitsA1(int n) {
        if(n==0)return new int[]{0};
        if(n==1)return new int[]{0,1};
        if(n==2)return new int[]{0,1,1};
        int[] nums = new int[n+1];
        nums[0]=0;
        nums[1]=1;
        nums[2]=1;
        for(int i=3; i<=n;i++){
            if(i%2==1)nums[i]=nums[i/2]+1;
            else nums[i]=nums[i/2];
        }
        return nums;
    }
}