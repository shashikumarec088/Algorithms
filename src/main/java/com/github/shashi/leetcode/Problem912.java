package com.github.shashi.leetcode;

public class Problem912 {
    public int[] sortArray(int[] nums) {
        int l=0, r = nums.length-1;
        rec(nums, l, r);
        return nums;
    }

    /*
        intuition is to merge recursively by deviding the array
        into half, we recursively sort 1st and 2nd half then merge
        the sorted part
    */
    public void rec(int[] nums, int l, int r){
        if(l<r){
            int m = (l+r)/2;
            rec(nums, l, m);
            rec(nums, m+1,r);
            merge(nums,l,m,r);
        }
    }

    public void merge(int[] nums, int l, int m, int r){
        int l1 = m-l+1, l2 = r-m, i1=0, i2=0;
        int[] a1 = new int[l1];
        int[] a2 = new int[l2];
        for(int i=l; i<=m; i++)
            a1[i1++] = nums[i];
        for(int i=m+1; i<=r; i++)
            a2[i2++] = nums[i];
        i1=0;
        i2=0;
        int i=l;
        while(i1<l1 && i2<l2){
            if(a1[i1] <= a2[i2])
                nums[i++] = a1[i1++];
            else nums[i++] = a2[i2++];
        }
        while(i1<l1)nums[i++] = a1[i1++];
        while(i2<l2)nums[i++] = a2[i2++];
    }
}