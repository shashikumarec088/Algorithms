package com.github.shashi.leetcode;

public class Problem88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        mergeA1(nums1,m,nums2,n);
    }

    public void mergeA1(int[] nums1,int m, int[] nums2, int n){
        int i=m-1,j=n-1,k=m+n-1;
        while(i>=0 && j>=0){
            if(nums1[i]>nums2[j])
                nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while(j>=0) nums1[k--] = nums2[j--];
    }
}
