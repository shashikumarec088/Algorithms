package com.github.shashi.leetcode;

public class Problem201 {
    /*
        Bitwise AND of Numbers Range
    Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

    Example 1:
    Input: left = 5, right = 7
    Output: 4
    Example 2:
    Input: left = 0, right = 0
    Output: 0
    Example 3:
    Input: left = 1, right = 2147483647
    Output: 0
    Constraints:
    0 <= left <= right <= 231 - 1

    Approach 1: bit shifting
    * intuition is that when we consider all the number within the range the and of all numbers is the common
    prefix among the numbers
                    5 -> 101
                    6 -> 110
                    7 -> 101
    * if we observe the above numbers we see that the common prefix of all the numbers is the ans, instead of
    computing the common prefix of all numbers if we compute the common prefix of 2 numbers it will be enough
    * to compute the common prefix we can shift both the number to right until they are equal and count the
    number if shifts we did and return the number by shifting it right same number of times
    algo:
    * init count=0, iterate until both are not equal
    * make left = left >>> 1 and right = right >>> 1 and inc count
    * return left <<< count
    time & space:
    * const time & space

    Approach 2: using n*n-1 to reset the lsb
    * intuition is that we can and n,n-1 to reset the lsb, we can use this fact to reduce the n until it is > m
    and when it becomes less or equal to m then it is the answer
    algo:
    * iterate until right > left
    * make right = right * (right-1)
    * return right
    time & space:
    * it takes const time and space
     */

    public int rangeBitwiseAnd(int left, int right) {
        return rangeBitwiseAndA2(left,right);
    }

    public int rangeBitwiseAndA2(int left, int right) {
        int i=0;
        while(right > left){
            right = right & (right-1);
        }
        return right;
    }

    public int rangeBitwiseAndA1(int left, int right) {
        int i=0;
        while(left!=right && left!=0){
            i++;
            left = left >>> 1;
            right = right >>> 1;
        }
        return left << i;
    }
}
