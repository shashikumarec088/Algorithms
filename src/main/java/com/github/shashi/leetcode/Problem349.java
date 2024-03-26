package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        return intersectionA2(nums1,nums2);
    }

    public int[] intersectionA2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        int n1 = nums1.length, n2=nums2.length,l1=0,l2=0;
        while(l1<n1 && l2<n2){
            if(nums1[l1]==nums2[l2]){
                set.add(nums1[l1]);
                l1++;
                l2++;
            }else if(nums1[l1]<nums2[l2])l1++;
            else l2++;
        }

        int[] ans = new int[set.size()];
        int i=0;
        for(int num: set) ans[i++]=num;
        return ans;
    }

    public int[] intersectionA1(int[] nums1, int[] nums2) {
        int[] cache = new int[1001];
        int count=0;
        for(int i=0; i<1001; i++)
            cache[i]=Integer.MAX_VALUE;
        for(int num: nums1){
            if(cache[num]==Integer.MAX_VALUE)cache[num]=1;
        }
        for(int num: nums2){
            if(cache[num]==1){
                count++;
                cache[num]=2;
            }
        }
        int[] res = new int[count];
        int j=0;
        for(int i=0;i<cache.length;i++){
            if(cache[i]==2)res[j++]=i;
        }
        return res;
    }
}