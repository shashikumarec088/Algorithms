package com.github.shashi.leetcode;

public class Problem58 {
    public int lengthOfLastWord(String s) {
        return lengthOfLastWordA1(s);
    }

    /*
    intuition is to check element from end stop when char is space
    and length is not zero
     */
    public int lengthOfLastWordA1(String s) {
        int n = s.length(), l=0;
        for(int i=n-1; i>=0; i--){
            if(s.charAt(i)==' ' && l==0)continue;
            else if(s.charAt(i)==' ')break;
            else l++;
        }
        return l;
    }
}