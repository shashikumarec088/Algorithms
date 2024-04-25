package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem202 {
    /*
    Happy Number
    Write an algorithm to determine if a number n is happy.
    A happy number is a number defined by the following process:

    Starting with any positive integer, replace the number by the sum of the squares of its digits.
    Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
    Those numbers for which this process ends in 1 are happy.
    Return true if n is a happy number, and false if not.

    Example 1:
    Input: n = 19
    Output: true
    Explanation:
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1

    Example 2:
    Input: n = 2
    Output: false

    Constraints:
    1 <= n <= 231 - 1

    approach 1:
    * intuition is in the process of computation if the number repeats then this is not a happy number
    else if it reduces to 1 then it is. How we can be sure that the number end up repeating instead
    of increasing infinitely is consider max 1 git 9 -> 81 similarly
    99 -> 162 999 -> 243 9999 -> 324 so for 3 digits it will max reach 243 and then starts to reduce
    either to 1 or keeps repeating.
    algo:
    * have a set to track the numbers generated so far, iterate until num =1 or num is present it set
    * if num is 1 return true else false
    * to generate a number we mod the number to get the digit and devide it by 10 to remove the
    processed digit
    time & space:
    * it takes log n  to generate the number by squaring the digits and 243 is the max numbers
    we can process hence overall it is log n and space is also 0(1) since max we hold 243 elements

    approach 2:
    * intuition is to use the floyds cycle detection algorithm, since we know from approach 1
    analysis that if number is not reduced to 1 then it has cycle
    algo:
    * define the slow as n and fast= getNext(n) where getNext is the sum of squares of each digit in n
    * iterate the loop until slow != fast and fast is not 1
    * at the end if fast=1 return true else false;
    time & space:
    * time complexity is log n for getting the next element since we do limitted computations on
    the overall time complexity is log n and space complexity is o(1) since we do not use any extra space
     */

    public boolean isHappy(int n) {
        return isHappyA2(n);
    }

    public boolean isHappyA2(int n) {
        int slow = n, fast = getNext(n);
        while(slow != fast && fast !=1){
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast==1;
    }

    public int getNext(int n){
        int sum = 0;
        while(n!=0){
            int digit = n%10;
            sum += digit*digit;
            n = n/10;
        }
        return sum;
    }

    public boolean isHappyA1(int n) {
        Set<Integer> seen = new HashSet<>();
        while(!seen.contains(n) && n >1){
            seen.add(n);
            n = getNext(n);
        }
        return n==1;
    }

}
