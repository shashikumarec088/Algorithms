package com.github.shashi.leetcode;

public class Problem344 {
    public void reverseString(char[] s) {
        reverseStringA2(s);
    }

    public void reverseStringA2(char[] s) {
        helper(s,0,s.length-1);
    }

    public void helper(char[] s, int l, int h){
        if(l>=h)return;
        char temp = s[l];
        s[l] = s[h];
        s[h] = temp;
        l++;
        h--;
        helper(s,l,h);
    }

    public void reverseStringA1(char[] s) {
        int i=0, j=s.length-1;
        while(i<j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}