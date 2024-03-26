package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Problem394 {
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