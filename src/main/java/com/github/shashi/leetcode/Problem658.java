package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem658 {

    public static void main(String[] args) {
        Problem658 prom = new Problem658();
        int[] nums = {1,1,1,10,10,10};
        prom.findClosestElements(nums,1,9);
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        return findClosestElementsA1(arr, k, x);
    }

    public List<Integer> findClosestElementsA1(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int l=0, r = arr.length-1, n = arr.length;
        while(l+1<r){
            int mid = l+(r-l)/2;
            if(arr[mid]>=x)r=mid;
            else l=mid;
        }
        if(Math.abs(arr[l]-x) <=Math.abs(arr[r]-x)) {
            r=l;
            l=l-1;
        }else{
            l=r-1;
        }
        while(l>=0 && r<n && (r-l-1)<k){
            int dl = Math.abs(arr[l]-x);
            int dr = Math.abs(arr[r]-x);
            if(dl<=dr)l--;
            else r++;
        }

        while((r-l-1)<k && l>=0)l--;
        while((r-l-1)<k && r<n)r++;

        for(int i=l+1 ; i<r; i++)
            res.add(arr[i]);
        return res;

    }
}