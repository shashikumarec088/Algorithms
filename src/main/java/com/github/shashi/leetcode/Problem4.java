package com.github.shashi.leetcode;

public class Problem4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMedianSortedArraysA2(nums1,nums2);
    }

    /*
     * for this the intuition is based on the binary search, we find
     * the partition in smaller array check if corresponding partition
     * in larger array meets the criteria of left part and right part
     * if not met find the partition in the other partition
     */
    public double findMedianSortedArraysA2(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length)return findMedianSortedArraysA2(nums2,nums1);
        int m=nums1.length, n=nums2.length;
        int l=0, r=m;
        while(l<=r){
            int partA = (l+r)/2;
            int partB = (m+n+1)/2-partA;
            int maxleftA = partA==0?Integer.MIN_VALUE:nums1[partA-1];
            int minrightA = partA==m?Integer.MAX_VALUE:nums1[partA];
            int maxleftB = partB==0?Integer.MIN_VALUE:nums2[partB-1];
            int minrightB = partB==n?Integer.MAX_VALUE:nums2[partB];

            if(maxleftA>minrightB)r=partA-1;
            else if(maxleftB>minrightA)l=partA+1;
            else{
                if((m+n)%2==0)
                    return (Math.max(maxleftA,maxleftB)+
                            Math.min(minrightA,minrightB))/(double)2.0;
                else return Math.max(maxleftA,maxleftB);
            }
        }
        return 0.0;
    }

    public double findMedianSortedArraysA1(int[] nums1, int[] nums2) {
        int m= nums1.length, n=nums2.length,i1=0,i2=0,i=0;
        int []out = new int[m+n];
        while(i1<m && i2<n){
            if(nums1[i1]<=nums2[i2]){
                out[i++]=nums1[i1++];
            }else out[i++]=nums2[i2++];
        }

        while(i1<m)out[i++]=nums1[i1++];
        while(i2<n)out[i++]=nums2[i2++];
        int mid = out.length/2;
        if(out.length %2 == 0) return (out[mid]+out[mid-1])/(double)2.0;
        return out[mid];
    }
}