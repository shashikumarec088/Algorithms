package com.github.shashi.leetcode;

public class Problem137 {
    /*
    Single Number II
    Given an integer array nums where every element appears three times except for one, which appears exactly once.
    Find the single element and return it.
    You must implement a solution with a linear runtime complexity and use only constant extra space.
    Example 1:
    Input: nums = [2,2,3,2]
    Output: 3
    Example 2:
    Input: nums = [0,1,0,1,0,1,99]
    Output: 99
    Constraints:
    1 <= nums.length <= 3 * 104
    -231 <= nums[i] <= 231 - 1
    Each element in nums appears exactly three times except for one element which appears once.

    Approach 1: bit manipulation
    * intuition is that if same number repeats 3 times then the same bit repeats 3 times, if that bit is 1
    then its count will be 3 if we mod each bit by 3 we end up getting either 0 or 1 based on the lone number bit.
    * at each bit level across the numbers if we count the set bits and mod it with 3 we will get the lone number bit
    algo:
    * init int variable lone to store the lone number bitwise, iterate from i=0 to <32
    * for each bit init curBitSum and iterate over all numbers
    * for each number shift num to right by i bits and & with 1 and add to curBitSum
    * mod the curBitSum with 3 and shift the result to left i bits and or the result with lone
    * return the lone as answer
    time & space:
    * it takes n time and const space

    Approach 2: equation by bitmask
    * intuition is to capture the set bit at each bit level which is repeating and remaining with 1 times after
    taking mod 3. for this we can have seenOnce which for reach number stores the bits that are not seen before
    and seenOnce will store the bits which are seen twice.
    * to capture the seenOnce bits we xor the seenOnce with the current number which makes sure that the once
    appearing bits are retained and we reset the bits which appeared twice before by anding with the not of seenTwice
    * to capture the seenTwice at each bit level we xor the seenTwice with current number and are not set in seenOnce
    here if the bit is set in seenOnce then it is appearing only once if it is not set then it appeared before also
    * this yields seenOnce = (seenOnce ^ num) & (~ seenTwice) nad seenTwice = (seenTwice ^ num) & (~ seenOnce)
    * once we process all numbers the bits left in seenOnce will hold the lone number
    algo:
    * init variable seenOnce =0 and seenTwice=0 of type integers
    * iterate over all numbers for each number calculate seenOnce = (seenOnce ^ num) & (~ seenTwice)
    and seenTwice = (seenTwice ^ num) & (~ seenOnce)
    * return seenOnce at the end
    time & space:
    * n time and const space
     */

    public int singleNumber(int[] nums) {
        return singleNumberA1(nums);
    }

    public int singleNumberA1(int[] nums) {
        int lone=0;
        for(int i=0; i<32; i++){
            int curSum=0;
            for(int num: nums)
                curSum+= (num >>> i)&1;
            lone |= (curSum%3) << i;
        }
        return lone;
    }

    public int singleNumberA2(int[] nums) {
        int seenOnce=0,seenTwice=0;
        for(int num: nums){
            seenOnce = (seenOnce ^ num) & (~seenTwice);
            seenTwice = (seenTwice ^ num) & (~seenOnce);
        }
        return seenOnce;
    }
}
