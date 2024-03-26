package com.github.shashi.leetcode;

public class Problem287 {
    public int findDuplicate(int[] nums) {
        return findDuplicateA5(nums);
    }

    /*
    * this is based on floids cycle detection
    * algorithm, where we move 2 pointers when they meet
    * we move the fast moving pointer to begining and
    * move both the pointers in same speed to find the
    begining of the cycle
    */
    public int findDuplicateA6(int[] nums) {
        int h=nums[0],t=nums[0];
        do{
            h=nums[nums[h]];
            t=nums[t];
        }while(h!=t);
        h=nums[0];
        while(h!=t){
            h=nums[h];
            t=nums[h];
        }
        return h;
    }

    /*
     * intuition is to find the number of elements less than
     * or equal to current number if it is greater then it is
     * duplicate and traverse left side
     */
    public int findDuplicateA5(int[] nums) {
        int l=1, r= nums.length-1, dup=0;
        while(l<=r){
            int mid = l +(r-l)/2;
            int count=0;
            for(int num:nums)
                if(num<=mid)count++;
            if(count>mid){
                dup=mid;
                r=mid-1;
            }else l=mid+1;
        }
        return dup;
    }

    /*
     * intuition is keep placing the numbers in right position
     * if any number repeats then this is the duplicate
     *
     */
    public int findDuplicateA4(int[] nums) {
        return rec(nums,nums[0]);
    }

    public int rec(int[]nums, int num){
        if(nums[num]==num)return num;
        int temp = nums[num];
        nums[num] = num;
        return rec(nums, temp);
    }

    public int findDuplicateA3(int[] nums) {
        int num = nums[0];
        while(nums[num]!= num){
            int temp = nums[num];
            nums[num] = num;
            num = temp;
        }

        return num;
    }

    public int findDuplicateA2(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++){
            int num = Math.abs(nums[i]);
            if(nums[num]<0)return num;
            nums[num] *= -1;
        }

        return -1;
    }

    public int findDuplicateA1(int[] nums) {
        int[] cache = new int[nums.length+1];
        for(int num: nums){
            if(cache[num]!=0)return num;
            cache[num]=1;
        }
        return -1;
    }
}