package com.github.shashi.leetcode;

public class Problem28 {
    public int strStr(String haystack, String needle) {
        return strStrA1(haystack,needle);
    }

    public int strStrA1(String haystack, String needle) {

        int i=0, m = haystack.length(), n = needle.length();
        while(i<=m-n){
            int j=i, k=0;
            while(j<m && k<n &&
                    haystack.charAt(j)== needle.charAt(k)){
                j++;
                k++;
            }
            if(k==n)return i;
            i++;
        }
        return -1;
    }
}