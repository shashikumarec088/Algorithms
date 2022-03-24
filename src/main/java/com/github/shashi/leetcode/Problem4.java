package com.github.shashi.leetcode;

public class Problem4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMedianSortedArraysA1(nums1, nums2);
    }

    public double findMedianSortedArraysA1(int[] nums1, int[] nums2){
        int m = nums1.length, n = nums2.length;
        if(m>n){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int temp2 = m;
            m = n;
            n = temp2;
        }
        int total = m+n, half = (m+n)/2;

        int l=0, r = m-1;
        while(true){
            // if dont use floor dive -1/2 we will get 0 which
            // will cause logical issue
            int i = Math.floorDiv(l+r,2);
            // subtract 2 because we need index not the count
            // 1 for 1st array and 1 for 2nd array
            int j = half - i -2;
            int aLeft = i>=0 ? nums1[i]: Integer.MIN_VALUE;
            int aRight = i+1 < m ? nums1[i+1]: Integer.MAX_VALUE;
            int bLeft = j>=0 ? nums2[j]: Integer.MIN_VALUE;
            int bRight = j+1<n? nums2[j+1]: Integer.MAX_VALUE;

            if(aLeft <= bRight && bLeft<=aRight){
                if(total%2==0)
                    return (Math.max(aLeft,bLeft)+Math.min(aRight,bRight))/2.0;
                else return Math.min(aRight,bRight);
            }

            else if(aLeft>bRight)
                r = i-1;
            else l = i+1;
        }
    }
}
