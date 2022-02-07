package com.github.shashi.leetcode;
import java.util.*;
public class Problem268 {
    public int missingNumber(int[] nums) {
        return missingNumsA4(nums);
    }

    public int missingNumsA4(int[] nums){
        Arrays.sort(nums);
        int i=0, j=nums.length-1;
        while(i<=j){
            int mid = i+(j-i)/2;
            if(nums[mid] == mid)
                i = mid+1;
            else j = mid-1;
        }
        return i;
    }

    public int missingNumsA3(int[] nums){
        int xor = nums.length;
        for(int i=0; i < nums.length; i++)
            xor ^= nums[i] ^ i;
        return xor;
    }

    public int missingNumsA2(int[] nums){
        int sum = nums.length;
        for(int i=0; i<nums.length; i++)
            sum += i - nums[i];
        return sum;
    }

    public int missingNumsA1(int[] nums){
        int n = nums.length, i=0;
        while(i<n){
            while(nums[i] != i){
                int temp = nums[i];
                if(temp < n){
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
                else break;
            }
            i++;
        }
        for(int j=0; j<n; j++)
            if(nums[j] != j) return j;
        return n;
    }
}
