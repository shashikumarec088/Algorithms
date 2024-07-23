package com.github.shashi.leetcode;

public class Problem66 {
    /*
    Plus One
    You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of
    the integer. The digits are ordered from most significant to least significant in left-to-right order. The large
    integer does not contain any leading 0's.
    Increment the large integer by one and return the resulting array of digits.
    Example 1:
    Input: digits = [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123.
    Incrementing by one gives 123 + 1 = 124.
    Thus, the result should be [1,2,4].
    Example 2:
    Input: digits = [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321.
    Incrementing by one gives 4321 + 1 = 4322.
    Thus, the result should be [4,3,2,2].
    Example 3:
    Input: digits = [9]
    Output: [1,0]
    Explanation: The array represents the integer 9.
    Incrementing by one gives 9 + 1 = 10.
    Thus, the result should be [1,0].
    Constraints:
    1 <= digits.length <= 100
    0 <= digits[i] <= 9
    digits does not contain any leading 0's.

    approach 1: with carry flag
    * intuition is to init carry =1 and keep doing the index wise sum from end until the we reach start and cary>0
    at the end if carry exists then that means we need to create new array and set the first index value to 1 else
    return the existing array.
    algo:
    * init n = digits.length, carry=1
    * iterate from i=n-1 to 0 and carry>0, for each value of i make carry = carry+nums[i], nums[i] = carry%10,
    * carry = carry/10;
    * if carry>0 then it is overflowing the array length create new array of length n+1 set 0th element to 1 and
    return
    * else return the nums
    time & space:
    * n time and n space if overflows else const space

    approach 2: same as approach1 but clean
    algo:
    * iterate from i=n-1 to 0
    * if nums[i] is 9 make 0 else inc nums[i] and return nums
    * if we come out of the loop then the result is overflowing create new array with size n+1 set 1st index
    value to 1 and return new array
    time & space:
    * n time and n space if we create new array else const space
     */
    public static void main(String[] args) {
        int[] digits = {9};
        Problem66 problem66 = new Problem66();
        int[] ans = problem66.plusOne(digits);
    }
    public int[] plusOne(int[] digits) {
        return plusOneA1(digits);
    }

    public int[] plusOneA2(int[] nums) {
        int n = nums.length;
        for(int i=n-1; i>=0; i--){
            if(nums[i]==9)
                nums[i]=0;
            else{
                nums[i]++;
                return nums;
            }
        }
        int[] res = new int[n+1];
        res[0]=1;
        return res;
    }

    public int[] plusOneA1(int[] digits) {
        int carry=1;
        int i = digits.length-1;
        while(i>=0 && carry>0){
            carry += digits[i];
            digits[i--] = carry%10;
            carry = carry/10;
        }
        if(carry>0){
            int[] res = new int[digits.length+1];
            res[0]=1;
            return res;
        }
        return digits;
    }
}
