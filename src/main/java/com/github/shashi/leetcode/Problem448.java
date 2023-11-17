package com.github.shashi.leetcode;
import java.util.*;

public class Problem448 {

    public static void main(String[] args) {
        Problem448 problem448 = new Problem448();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(problem448.findDisappearedNumbers(nums));
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        return findDisappearedNumbersA2(nums);
    }

    public List<Integer> findDisappearedNumbersA2(int[] nums){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            int t1 = nums[i];
            while(t1>=0 && !(nums[t1-1]<0)){
                int t2 = nums[t1-1];
                nums[t1-1] = -t2;
                t1 = t2;
            }
        }
        for(int i=0; i<nums.length; i++)
            if(nums[i]>0)list.add(i+1);
        return list;
    }

    public List<Integer> findDisappearedNumbersA1(int[] nums){
        int[] nn = new int[nums.length+1];
        for(int n : nums)
            nn[n]=1;
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<nn.length; i++)
            if(nn[i]==0)
                list.add(i);
        return list;
    }
}