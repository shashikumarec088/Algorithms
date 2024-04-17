package com.github.shashi.leetcode;

public class Problem125 {
    public boolean isPalindrome(String s) {
        return isPalindromeA2(s);
    }

    public boolean isPalindromeA2(String s) {
        String s1 = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        return s1.equals(new StringBuilder(s1).reverse().toString());
    }

    /*
    intuition is to use 2 pointer and skip comparing when
    character is not alpha numeric 
     */
    public boolean isPalindromeA1(String s) {
        int n = s.length();
        int i=0, j=n-1;
        while(i<j){
            char c1 = Character.toLowerCase(s.charAt(i));
            char c2 = Character.toLowerCase(s.charAt(j));;
            if(!Character.isLetterOrDigit(c1)){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(c2)){
                j--;
                continue;
            }
            if(c1!=c2)return false;
            i++;
            j--;
        }
        return true;
    }
}