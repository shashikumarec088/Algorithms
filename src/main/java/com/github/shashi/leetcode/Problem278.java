package com.github.shashi.leetcode;

public class Problem278 {
    public int firstBadVersion(int n) {
        return firstBadVersionA1(n);
    }

    public int firstBadVersionA1(int n) {
        int l=1, r=n;
        while(l<r){
            int mid = l+(r-l)/2;
            boolean bad = isBadVersion(mid);
            if(bad)r=mid;
            else l=mid+1;
        }
        return l;
    }

    private boolean isBadVersion(int index){
        return true;
    }
}