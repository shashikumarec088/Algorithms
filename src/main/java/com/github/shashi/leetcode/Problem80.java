package com.github.shashi.leetcode;

public class Problem80 {
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesA2(nums);
    }

    /*
    inspired from leetcode comments, idia is to
    start from 2nd index if curent element is same
    compared to previous end element then this should
    be replaced else we need to increment i and proceed
    */
    public int removeDuplicatesA2(int[] nums){
        int i=2;
        for(int j=2; j< nums.length;j++){
            nums[i]=nums[j];
            if(nums[i]!=nums[i-2])i++;
        }
        return i;
    }
    /*
    intuition is to have the index i and start and end indexes
    keep increasing the end index until no dups when no dups
    update the index values with start index value, if difference
    is more than 1 then copy twice. remember to handle the case
    when you reach the last element, that will not be copied so
    copy it, if last element has dups then copy twice
    */
    public int removeDuplicatesA1(int[] nums) {
        int i=0, s=0, e=1,n=nums.length;
        while(e<n){
            if(nums[s]==nums[e])e++;
            else{
                nums[i++]=nums[s];
                if(e-s > 1)nums[i++]=nums[s];
                s=e;
                e++;
            }
        }
        nums[i++]=nums[s++];
        if(s!=e)nums[i++]=nums[s];
        return i;
    }
}