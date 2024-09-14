package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem169 {
    /*
    169. Majority Element
    Given an array nums of size n, return the majority element.

    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
    element always exists in the array.
    Example 1:
    Input: nums = [3,2,3]
    Output: 3
    Example 2:
    Input: nums = [2,2,1,1,1,2,2]
    Output: 2
    Constraints:
    n == nums.length
    1 <= n <= 5 * 104
    -109 <= nums[i] <= 109
    Follow-up: Could you solve the problem in linear time and in O(1) space?

    Approach 1: map approach
    * intuition is to count the frequencies of all the elements and consider the element with high freq
    algo:
    * init map of type int,int, elem as nums[0]
    * iterate over the nums for each num
    * put to map with updated frequency
    * if cur element freq is > elem freq then make cur element as elem
    * return elem at the end
    time & space:
    * it takes n time and n space

    Approach 2: sort approach
    * intuition is to sort the array and consider the element at n/2 position
    algo:
    * sort the array using Arrays.sort(nums)
    * return element at n/2;
    time & space:
    * it takes nlogn time n space

    Approach 3: bit manipulation
    * intuition is since the majority element repeats more than n/2 times then, we can build the majority element
    bit by bit, at each bit across the number if bit set count is > n/2 then the majority element bit is set.
    we can do this across the bits and set the corresponding bit in majority element accordingly
    * algo:
    * int n as number of elements, majority=0
    * iterate from i=0 to <32
    * int bit = 1 << i  and count=0
    * iterate over numbers and if num&bit != 0 inc count
    * if count > n/2 then make majority |= bit
    * return majority at the end
    time & space:
    * it takes the const space and n time

    Approach 4: boyer-moore intuition
    * intuition is based on the boyer-moore algorithm where if any number repeats more than n/2 then,
    when as consider the element from start and count as 1 and keep inc the count if we see same number next
    else dec count, then when count becomes 0 then update the number this will always lead to majority element
    algo:
    * init count=0, candidate=0
    * iterate over numbers
    * if count is 0 then make num as candidate
    * if candidate is same as num then add +1 or -1 to count
    time & space:
    * it takes const space and n time.
     */
    public int majorityElement(int[] nums) {
        return majorityElementA4(nums);
    }

    /*
    intuition behind the approach is based on the boyer-moore
    algorithm, this algo states to keep counting the number
    of times the number occurred and discounting the mismatched
    number when count is zero update the candidate, this will
    work because major numbor appears more than half
    */
    public int majorityElementA4(int[] nums) {
        int count=0;
        int candidate=0;
        for(int num : nums){
            if(count==0){
                candidate=num;
            }
            count += candidate==num?+1:-1;
        }
        return candidate;
    }

    public int majorityElementA3(int[] nums) {
        int n = nums.length;
        int major = 0;
        for(int i=0; i<32;i++){
            int bit=1<<i;
            int cn=0;
            for(int num:nums)
                if((num & bit) !=0)
                    cn++;
            if(cn>n/2)
                major = major | bit;
        }
        return major;
    }

    public int majorityElementA2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public int majorityElementA1(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int major = nums[0];
        for(int i=0; i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            if(map.get(nums[i])>map.get(major))
                major=nums[i];
        }
        return major;
    }
}