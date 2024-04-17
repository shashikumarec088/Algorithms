package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem274 {
    public int hIndex(int[] citations) {
        return hIndexA3(citations);
    }

    /*
        intuition is to count the numbers, since max value of
        can be size of array if values in array are greater then value then
        we can consider size as value.
        iterate downwords considering n as h
        untill the sum is less than h;

    */
    public int hIndexA3(int[] c){
        int n=c.length,h=0,s=0;
        int[] counts = new int[n+1];
        int rem=n;
        for(int num: c)
            counts[Math.min(num,n)]++;
        h = n;
        s = counts[n];
        while(h>s){
            h--;
            s+=counts[h];
        }
        return h;
    }

    /*
    sort array in desc and then iterate from begining
    until arr[i]>i . here in below code i am doing the same
    but from end since array default sorts in asc
    */
    public int hIndexA2(int[] citations) {
        Arrays.sort(citations);
        int h=0, n =citations.length;
        while(h<n && citations[n-h-1]>h)
            h++;
        return h;
    }

    /*
    intuition is to sort the array then start from
    0th index, keep h=0 , within each index increment
    the h until it is less than number of elements remaining in array
     and less than element at that index
    */
    public int hIndexA1(int[] citations) {
        Arrays.sort(citations);
        int h=0, n =citations.length;
        for(int i=0; i<n;i++){
            while(h<n-i && h < citations[i]){
                h++;
            }
        }
        return h;
    }
}