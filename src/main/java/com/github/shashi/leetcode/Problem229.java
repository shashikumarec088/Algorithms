package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem229 {
    public List<Integer> majorityElement(int[] nums) {
        return majorityElementA1(nums);
    }

    public List<Integer> majorityElementA1(int[] nums) {
        int c1=0,c2=0,n=nums.length;
        Integer m1=null,m2=null;
        for(int num:nums){
            if(m1!=null && m1==num)
                c1++;
            else if(m2!=null && m2==num)
                c2++;
            else if(c1==0){
                m1=num;
                c1++;
            }else if(c2==0){
                m2=num;
                c2++;
            }else{
                c1--;
                c2--;
            }
        }
        c1=0;
        c2=0;
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            if(m1!=null && num==m1)c1++;
            if(m2!=null && num==m2)c2++;
        }
        if(c1>n/3)list.add(m1);
        if(c2>n/3)list.add(m2);
        return list;
    }
}