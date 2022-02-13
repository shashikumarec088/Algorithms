package com.github.shashi.leetcode;

public class Problem27 {
    public int removeElement(int[] nums, int val) {
        return removeElementA2(nums,val);
    }

    public int removeElementA1(int[] a, int val){
        int n = a.length, i=0, j=0;
        while(j<n){
            if(a[j] != val)
                a[i++] = a[j];
            j++;
        }
        return i;
    }

    public int removeElementA2(int[] a, int val){
        int n = a.length, i=0;
        while(i<n){
            if(a[i] == val){
                a[i] = a[n-1];
                n--;
            }else i++;
        }
        return n;
    }
}
