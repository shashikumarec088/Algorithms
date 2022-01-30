package com.github.shashi.leetcode;
import java.util.*;
public class Problem15 {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumSet(nums);
    }

    public List<List<Integer>> threeSumNoSort(int[] nums){
        Set<Integer> dup = new HashSet<>();
        int n= nums.length;
        Set<List<Integer>> result = new HashSet<>();
        for(int i=0; i<n; i++){
            if(dup.add(nums[i])){
                Set<Integer> seen = new HashSet<>();
                for(int j=i+1; j<n; j++){
                    int sum = nums[i]+nums[j];
                    if(seen.contains(-sum)){
                        List<Integer> list = Arrays.asList(nums[i],nums[j],-sum);
                        Collections.sort(list);
                        result.add(list);
                    }
                    seen.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> threeSumSet(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<nums.length&& nums[i]<=0; i++){
            if(i>0 && nums[i-1]==nums[i]) continue;
            twoSumSet(i,nums,result);
        }
        return result;
    }

    public void twoSumSet(int i, int[] nums, List<List<Integer>> result){
        Set<Integer> set = new HashSet<>();
        for(int j=i+1; j<nums.length; j++){
            int sum = nums[i]+nums[j];
            if(set.contains(-sum)){
                result.add(Arrays.asList(nums[i],nums[j],-sum));
                while(j+1<nums.length && nums[j] == nums[j+1])j++;
            }
            set.add(nums[j]);
        }
    }

    public List<List<Integer>> threeSumSort(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<nums.length && nums[i]<=0; i++){
            if(i>0 && nums[i-1] == nums[i])continue;
            twoSum(nums,i,result);
        }
        return result;
    }

    public void twoSum(int[] nums, int i, List<List<Integer>> result){
        int l=i+1,h=nums.length-1;
        while(l<h){
            int sum = nums[i]+nums[l]+nums[h];
            if(sum==0) {
                result.add(Arrays.asList(nums[i],nums[l++],nums[h--]));
                while(l<h& nums[l]==nums[l-1])l++;
            }else if(sum>0)h--;
            else l++;
        }
    }
}
