package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem191 {
    /*
    Number of 1 Bits
    Write a function that takes the binary rep
    Example 1:
    Input: n = 11
    Output: 3
    Explanation:
    The input binary string 1011 has a total of three set bits.
    Example 2:
    Input: n = 128
    Output: 1
    Explanation:
    The input binary string 10000000 has a total of one set bit.
    Example 3:
    Input: n = 2147483645
    Output: 30
    Explanation:
    The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
    Constraints:
    1 <= n <= 231 - 1
    Follow up: If this function is called many times, how would you optimize it?

    Approach 1: bit shifting
    * intuition is to do what is asked for, iterate over each bit of input and if it is set inc count.
    algo:
    * init count=0, iterate until n !=0,
    * add n&1 to count and do signed shift of n one position to the right
    * return count
    time & space:
    * if n has all bits set in worst case we will iterate 32 times but in terms of complexity we can
    tell it as const time since it does not change for any value of n.

    Approach 2: bit masking
    * intuition is very similar to approach 1 but instead of shifting n we shift 1 to rightside and check
    by anding with n if that bit is set and inc the count
    algo:
    * init count=0, mask=1
    * iterate for i=0 to 32, for each iteration inc count if n&mask !=0
    * shift mask 1 bit left
    * return count
    time & space:
    * same as approach 1, const time & space

    Approach 3: bit manipulation
    * intuition is using the std algo fact that when we and the n with n-1 it resets the lsb 1 to 0, iterate
    until n is not zero and keep reset the lsb 1 to 0. in this approach we will iterate only number of bits
    set in n this is most optimal approach
    algo:
    * init count=0 , iterate until n !=0
    * inc count make n = n & (n-1)
    * return count
    time & space:
     * in worst case we repeat 31 times but in most of the cases we repeat only number of set bits times,
     but time & space are const in terms of representation.
     */
    public int hammingWeight(int n) {
        return hammingWeightA2(n);
    }

    public int hammingWeightA2(int n){
        int count=0,mask=1;
        for(int i=0; i<32; i++){
            if((n&mask) != 0)count++;
            mask<<=1;
        }
        return count;
    }

    public int hammingWeightA1(int n) {
        int count=0;
        while(n!=0){
            count+= n&1;
            n=n >>> 1;
        }
        return count;
    }

    public int hammingWeightA3(int n) {
        int count=0;
        while(n!=0){
            count++;
            n&=(n-1);
        }
        return count;
    }
}