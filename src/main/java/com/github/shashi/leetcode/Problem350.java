package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        return intersectionA2(nums1,nums2);
    }

    public int[] intersectionA2(int[] nums1, int[] nums2) {
        int[] cache = new int[1001];
        List<Integer> list = new ArrayList<>();
        int count=0;
        for(int num: nums1){
            cache[num]++;
        }
        for(int num: nums2){
            if(cache[num]!=0){
                list.add(num);
                cache[num]--;
            }
        }
        int[] res = new int[list.size()];
        int j=0;
        for(int elem : list){
            res[j++]=elem;
        }
        return res;
    }

    public int[] intersectionA1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> set = new ArrayList<>();
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
}