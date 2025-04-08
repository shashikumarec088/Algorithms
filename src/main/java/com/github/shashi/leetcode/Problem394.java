package com.github.shashi.leetcode;

import java.util.*;

public class Problem394 {
    /*
    394. Decode String

    Given an encoded string, return its decoded string.

    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
    repeated exactly k times. Note that k is guaranteed to be a positive integer.

    You may assume that the input string is always valid; there are no extra white spaces, square brackets are
    well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that
    digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

    The test cases are generated so that the length of the output will never exceed 105.

    Example 1:

    Input: s = "3[a]2[bc]"
    Output: "aaabcbc"
    Example 2:

    Input: s = "3[a2[c]]"
    Output: "accaccacc"
    Example 3:

    Input: s = "2[abc]3[cd]ef"
    Output: "abcabccdcdcdef"


    Constraints:

    1 <= s.length <= 30
    s consists of lowercase English letters, digits, and square brackets '[]'.
    s is guaranteed to be a valid input.
    All the integers in s are in the range [1, 300].

    Approach 1: using stack
    * intuition is to use stack to store the characters until we see the end bracket and process the subtring
    from the stack until we see the opening bracket.
    algo:
    * create the stack of characters, init n = length of string
    * iterate from i =0 to n
        * char c = char at i
        * check if c == ]
            * create the list of characters to hold the substring
            * iterate until stack is not empty and peek is not [
                * add the chars to list
            * pop the opening bracket from stack
            * create the num = 0, pos = 1
            * iterate until stack is not empty and peek is digit
                * num += (pop - '0')*pos
                * pos *= 10
            * iterate from k = num to 0
                * iterate from j = word.size-1 to 0
                    * push the char to stack
        * else push the char to stack
    * create the string builder to hold the result
    * iterate over the stack and append to string builder
    * return the string builder at the end
    time & space:
    * time complexity is if there is countk nested depth and k is the max count then
    * time is O(k^countk * n) and space O(k^countk * n)

    Approach 2: using 2 stacks
    * intuition is to use 2 stacks one for the digits and one for the string builder
    * when we see the digit we keep adding to the digit stack and when we see the opening bracket
    * we push the digit to the digit stack and create a new string builder
    * when we see the closing bracket we pop the digit and string builder and append the string builder
    algo:
    * create the stack of digits and string builder
    * init digit = 0, string builder = new string builder
    * iterate over the string
        * char c = char at i
        * if c is digit then digit = digit*10 + c - '0'
        * else if c == [
            * push the digit to stack
            * push the string builder to stack
            * create new string builder
        * else if c == ]
            * pop the digit and string builder from stack
            * append the string builder to prev string builder k times
        * else append the char to string builder
    * return the string builder at the end
    time & space:
    * time complexity is maxk * n and space is m+n where m chars and n number of digits

     */
    public static void main(String[] args) {
        Problem394 sol = new Problem394();
        System.out.println(sol.decodeString("3[a]2[bc]"));
    }
    public String decodeString(String s) {
        return decodeStringA1(s);
    }

    public String decodeStringA2(String s){
        Stack<Integer> stackInt = new Stack<>();
        Stack<StringBuilder> stackStr = new Stack<>();
        Integer digit = 0;
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(Character.isDigit(c)){
                digit = digit*10 + c - '0';
            }else if(c == '['){
                stackInt.push(digit);
                digit = 0;
                stackStr.push(sb);
                sb = new StringBuilder();
            }else if(c==']'){
                StringBuilder prev = stackStr.pop();
                for(int k = stackInt.pop(); k>0; k--){
                    prev.append(sb);
                }
                sb = prev;
            }else{
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public String decodeStringA1(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<n;i++){
            char c = s.charAt(i);
            if(c ==']'){
                List<Character> word = new ArrayList<>();
                while(!stack.isEmpty() && stack.peek()!='[')
                    word.add(stack.pop());
                stack.pop();
                int num=0,pos=1;
                while(!stack.isEmpty() && Character.isDigit(stack.peek())){
                    num += (stack.pop() - '0')*pos;
                    pos*=10;
                }
                for(int k=0; k<num;k++){
                    for(int j=word.size()-1; j>=0;j--)
                        stack.push(word.get(j));
                }
            } else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for(char c: stack)sb.append(c);
        return sb.toString();
    }

    public String decodeStringA11(String s) {
        int i=0, n = s.length();
        StringBuilder sb = new StringBuilder();
        Set<Character> digits = new HashSet<>();
        digits.addAll(Arrays.asList('1','2','3','4','5','6','7','8','9','0'));
        while(i<n){
            char c = s.charAt(i);
            if(digits.contains(c)){
                int openIndex = s.indexOf('[',i);
                int endIndex = s.indexOf(']',i);
                Integer num = Integer.valueOf(s.substring(i,openIndex));
                String seq = s.substring(openIndex+1,endIndex);
                for(int j=0; j<num; j++){
                    sb.append(seq);
                }
                i = endIndex+1;
            }else{
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }
}