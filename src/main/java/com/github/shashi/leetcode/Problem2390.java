package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem2390 {
    /*
    2390. Removing Stars From a String

    You are given a string s, which contains stars *.

    In one operation, you can:

    Choose a star in s.
    Remove the closest non-star character to its left, as well as remove the star itself.
    Return the string after all stars have been removed.

    Note:

    The input will be generated such that the operation is always possible.
    It can be shown that the resulting string will always be unique.


    Example 1:

    Input: s = "leet**cod*e"
    Output: "lecoe"
    Explanation: Performing the removals from left to right:
    - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
    - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
    - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
    There are no more stars, so we return "lecoe".
    Example 2:

    Input: s = "erase*****"
    Output: ""
    Explanation: The entire string is removed, so we return an empty string.


    Constraints:

    1 <= s.length <= 105
    s consists of lowercase English letters and stars *.
    The operation above can be performed on s.

    Approach 1: stack approach
    * intuition is to push the non star chars to stack and remove char from stack if cur char is star
    algo:
    * init stack of chars
    * iterate over chars of s
        * if c is * pop from stack
        * else push c to stack
    * create string builder
    * iterate over stack
        * append to sb
    * return sb.toString()
    time & space:
    * it takes n time and n space

    Approach 2: only with stringBuilder
    * intuition is same as approach 1 instead of using the stack we can use string builder directly
    append the char to sb if not star else remove last char from sb
    algo:
    * init string builder sb
    * iterate over the chars
        * if c is * remove last char from the sb
        * else append c to sb
    * return sb.toString()
    time & space:
    * it takes n time and n space

    Approach 3: 2 pointer approach
    * intuition is to have array of size n and pointer to hold the recent non star position
    algo:
    * init char array of size n init j=0
    * iterate i 0 to n
        * if c is * dec j
        * else make arr[j++] = c
    * create string builder and copy chars till j from array
    * return sb.toString()
    time & space:
    * it takes n time and n space
     */

    public String removeStars(String s) {
        return removeStarsA3(s);
    }

    public String removeStarsA1(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(c=='*'){
                if(!stack.isEmpty())stack.pop();
            }else stack.push(c);
        }
        for(char c: stack)sb.append(c);
        return sb.toString();
    }

    public String removeStarsA2(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(c=='*')
                sb.deleteCharAt(sb.length()-1);
            else sb.append(c);
        }
        return sb.toString();
    }

    public String removeStarsA3(String s) {
        int n = s.length(),j=0;
        char[] arr = new char[n];
        for(int i=0; i<n;i++){
            if(s.charAt(i)=='*')j--;
            else arr[j++] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<j;i++)
            sb.append(arr[i]);
        return sb.toString();
    }
}
