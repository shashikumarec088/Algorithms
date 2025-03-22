package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem1679 {
    /*
    1679. Max Number of K-Sum Pairs

    You are given an integer array nums and an integer k.

    In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

    Return the maximum number of operations you can perform on the array.



    Example 1:

    Input: nums = [1,2,3,4], k = 5
    Output: 2
    Explanation: Starting with nums = [1,2,3,4]:
    - Remove numbers 1 and 4, then nums = [2,3]
    - Remove numbers 2 and 3, then nums = []
    There are no more pairs that sum up to 5, hence a total of 2 operations.
    Example 2:

    Input: nums = [3,1,3,4,3], k = 6
    Output: 1
    Explanation: Starting with nums = [3,1,3,4,3]:
    - Remove the first two 3's, then nums = [1,4,3]
    There are no more pairs that sum up to 6, hence a total of 1 operation.


    Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 109
    1 <= k <= 109

    Approach 1: brute force nested loops
    * intuition is to iterate over i= 0  to n and j=i+1 to n when values at pos is zero then skip,
    when sum of i,j is target then inc count and reset values at i,j to 0
    algo:
    * init count =0
    * iterate i = 0 to n
        * check if nums[i] = 0 then continue
        * iterate j = i+1 to n
            * check if nums[j] == 0 then continue
            * check if nums[i] + nums[j] == k
                * inc count
                * make nums[i] = nums[j] = 0
                * break (since we can use each number only once)
    * return count;
    time & space:
    * n2 time and const space

    Approach 2: using hashMap
    * intuition is very similar to twosum problem here instead of string the index for the value we will
    store the frequecy as numbers can repeat and when we find the target then we count the pairs and dec the number
    algo:
    * init map of int, int, count=0, n
    * iterate from i=0 to n
        * cur = nums[i] and complement = k - cur
        * if map.get(complement) > 0
            * inc count and dec freq in map
        * else put cur in map and inc freq

    * return count
    time & space:
    * it takes n time and n space

    Approach 3: intuition is to sort the array and use 2 pointer
    * intuition is to sort the array and then use 2 pointers then when sum is k then inc count
    algo:
    * sort array using Arrays.sort(nums)
    * init i=0, j=n-1
    * iterate until i<j
        * sum = nums[i]+nums[j]
        * if sum > k
            dec j
        * else if sum < k
            inc i
        * else
            * inc count
            * inc i dec j
    * return count
    time & space:
    *  n log n time and const space
     */

    public int maxOperations(int[] nums, int k) {
        return maxOperationsA2(nums, k);
    }

    public int maxOperationsA3(int[] nums, int k) {
        Arrays.sort(nums);
        int count=0,n = nums.length;
        int i=0, j=n-1;
        while(i<j){
            int sum = nums[i]+nums[j];
            if(sum > k)j--;
            else if(sum<k)i++;
            else{
                count++;
                i++;
                j--;
            }
        }
        return count;
    }

    public int maxOperationsA2(int[] nums, int k) {
        int count=0,n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<n;i++){
            int cur = nums[i], complement = k - nums[i];
            if(map.getOrDefault(complement,0)>0){
                count++;
                map.put(complement,map.get(complement)-1);
            }else map.put(cur,map.getOrDefault(cur,0)+1);
        }
        return count;
    }


    public int maxOperationsA1(int[] nums, int k) {
        int count=0,n = nums.length;
        for(int i=0; i<n; i++){
            if(nums[i]==0)continue;
            for(int j=i+1; j<n; j++){
                if(nums[j]==0)continue;
                if((nums[i]+nums[j])==k){
                    count++;
                    nums[i]=0;
                    nums[j]=0;
                    break;
                }
            }
        }
        return count;
    }
}
