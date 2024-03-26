package com.github.shashi.leetcode;

public class Problem69 {
    public int mySqrt(int x) {
        return mySqrtA2(x);
    }

    public int mySqrtA2(int x) {
        if(x< 2)return x;
        int left = mySqrtA2(x/4)*2;
        int right = left+1;
        return (long)right*right > x?left:right;
    }

    public int mySqrtA1(int x) {
        if(x<=1)return x;
        int l=2, r = x/2;
        while(l<=r){
            int mid = l+(r-l)/2;
            long res = (long)mid*mid;
            if(res==x)return mid;
            else if(res <x) l = mid+1;
            else r = mid-1;
        }
        return r;
    }
}