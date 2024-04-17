package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class Problem189 {
    public void rotate(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        k = k%nums.length;
        rotateA3(nums,k);
    }

    /*
    intuition is to start the swaping process form 0th index,
    continue the process until you reach the same position,
    keep the count of swaps once we reach the same position,
    if count is n then we moved all the elements to right position
    if count < n then we need to move few more elements increment
    the position and repeat the process, rememer this is done
    using the do while loop
*/
    public void rotateA3(int[] nums, int k){
        int count =0, n = nums.length;
        for(int st=0; count<n; st++){
            int cur=st;
            int elem = nums[st];
            do{
                int pos = (cur+k)% n;
                int temp = nums[pos];
                nums[pos] = elem;
                elem = temp;
                cur = pos;
                count++;
            } while(st != cur);
        }
    }
    /*
        intuition is to reverse the left and right part then
        reverse the complete array remember this
    */
    public void rotateA2(int[] nums, int k){
        int n = nums.length;
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k,n-1);
        reverse(nums, 0, n-1);
    }

    public void reverse(int[] nums, int l, int h){
        while(l<h){
            int temp = nums[l];
            nums[l] = nums[h];
            nums[h] = temp;
            l++;
            h--;
        }
    }

    public void rotateA1(int[] nums, int k) {
        int n = nums.length,j=0;
        int[] arr = new int[n];
        for(int i=n-k; i<n; i++){
            arr[j++] = nums[i];
        }
        for(int i=0; i<n-k;i++){
            arr[j++] = nums[i];
        }
        for(int i=0; i<n; i++){
            nums[i] = arr[i];
        }
    }
}