package com.github.shashi.leetcode;

public class Problem9 {
    /*
    Palindrome Number
    Given an integer x, return true if x is a palindrome, and false otherwise.
    Example 1:
    Input: x = 121
    Output: true
    Explanation: 121 reads as 121 from left to right and from right to left.
    Example 2:
    Input: x = -121
    Output: false
    Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a
    palindrome.
    Example 3:
    Input: x = 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
    Constraints:
    -231 <= x <= 231 - 1
    Follow up: Could you solve it without converting the integer to a string?

    Approach 1: reversing the complete number
    * intuition is to reverse the number from end by considering each digit by doing mod on number, we multiply
    the reversed number with 10 before adding the last digit
    algo:
    * init cur=x, rev=0; we check if num<0 or num>0 and num%10 == 0 if so we return false since it cant be
    palindrome
    * iterate until cur !=0 and make rev = rev*10 + cur%10 and make cur = cur/10
    * return rev==x;
    time & space:
    * takes log n base 10 time and const space

    Approach 2: reversing half number
    * intuition is to reverse the half number and compare if equal to remaining number, to stop when half
    reversed we can compare the left over number with reversed number when it is still > reversed number then
    we need to continue else stop.
    * we need handle the case when odd digits are present in that case we need to ignore the last digit in the reversed
    number this is done by deviding it by 10
    algo:
    * init cur=x,rev=0, iterate until cur>rev
    * make rev = rev*10+cur%10, cur=cur/10
    * return cur==rev or cur == rev/10 (this is to handle the odd digits)
    tume & space:
    * it takes log n base 10 time and const space
     */

    public boolean isPalindrome(int x) {
        return isPalindromeA2(x);
    }

    public boolean isPalindromeA2(int x) {
        int cur=x,rev=0;
        if(cur<0 || (cur>0 && cur%10==0))return false;
        while(cur>rev){
            rev = rev*10+cur%10;
            cur=cur/10;
        }
        return rev==cur || cur == rev/10;
    }

    public boolean isPalindromeA1(int x) {
        int cur=x,rev=0;
        if(cur<0)return false;
        while(cur!=0){
            rev = rev*10+cur%10;
            cur=cur/10;
        }
        return rev==x;
    }
}
