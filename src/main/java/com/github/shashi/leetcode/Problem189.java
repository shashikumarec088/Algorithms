package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class Problem189 {
    /*
    189. Rotate Array
    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

    Example 1:
    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
    Example 2:
    Input: nums = [-1,-100,3,99], k = 2
    Output: [3,99,-1,-100]
    Explanation:
    rotate 1 steps to the right: [99,-1,-100,3]
    rotate 2 steps to the right: [3,99,-1,-100]

    Constraints:
    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    0 <= k <= 105
    Follow up:
    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
    Could you do it in-place with O(1) extra space?

    Approach 1: brute force approach TLE
    * intuition is to do what is asked for, we rotate the array k times each element at a time.
    algo:
    * int n as number of elements, k as k%n this is handle the case when k>=n;
    * iterate from i=0 to i<k for each iteration
    * make temp=nums[n-1]
    * iterate from j=n-1 to j>=1
    * make nums[j] = nums[j-1]
    * after iteration make nums[0]=temp
    time & space:
    * it takes kn time and constant space

    Approach 2: using extra array
    * intuition is to have the extra array and copy the elements to right position after rotation. and then
    copy back the result to original array
    algo:
    * init n as number of elements
    * create new array arr of size n
    * iterate from i=0 to i<n
    * make arr[(i+k)%n]=nums[i]
    * iterate once again from i=0 to i<n
    * make nums[i]=arr[i]
    time & space:
    * it takes n space and n time

    Approach 3: Using cyclic replacements
    * intuition is to start from the first element and keep placing it in its right position and
    take the element at that position and repeat this until we shift all the elements, if we areach the
    same position again then we start the process from next position, this process we continue until
    we shifted all the elements
    algo:
    * init n as number of elements count=0 and make k=k%n
    * iterate from i=0 to count < n
    * for each iteration make pos=i, temp=nums[i]
    * within do while loop
    * get newpos as (pos+k)%n and also newVal as nums[newpos]
    * update nums[newpos] = temp
    * make temp=newVal and pos=newpos
    * inc count
    * in while check condition  is pos!=i
    time & space:
    * it takes n time and constant space.

    Approach 4: using reverse
    * intuition is that when we rotate an array by k elements the last k elements will be at the first and
    first n-k elements will be at the last. we can get all the elements in reverse order by reversing all
    elements.
    ex : 1,2,3,4,5,6,7 k=3
    reverse 7,6,5,4,3,2,1   ans : 5,6,7,1,2,3,4
    if we compare the reverse with ans if we reverse the first k element and last n-k elements
    seperatly then we get the ans
    algo:
    * init n as number of elements
    * call reverse with 0,n-1
    * call reverse with 0,k-1
    * call reverse with k,n-1
    * in reverse we take i, j iterate until i<j
    * swap i, j elements inc i, dec j
    time & space:
    * it takes n time and constant space
     */
    public void rotate(int[] nums, int k) {
        rotateA3(nums,k);
    }

    public void rotateA4(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }

    public void reverse(int[] nums, int i, int j){
        while(i<j){
            int temp = nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
    }

    public void rotateA3(int[] nums, int k) {
        int n=nums.length,count=0;
        k=k%n;
        for(int i=0; count<n;i++){
            int pos=i,temp=nums[i];
            do{
                int npos = (pos+k)%n;
                int nval = nums[npos];
                nums[npos]=temp;
                temp=nval;
                pos=npos;
                count++;
            }while(pos!=i);
        }
    }

    public void rotateA2(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        int[] arr = new int[n];
        for(int i=0; i<n;i++)
            arr[(i+k)%n]=nums[i];
        for(int i=0; i<n;i++)
            nums[i]=arr[i];
    }

    public void rotateA1(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        for(int i=0; i<k;i++){
            int temp=nums[n-1];
            for(int j=n-1; j>=1; j--){
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }
}