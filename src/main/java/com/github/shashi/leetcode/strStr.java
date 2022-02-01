package com.github.shashi.leetcode;

public class strStr {

    public int strStr(String haystack, String needle) {
        return strIndex(haystack, needle);
    }

    public static void main(String[] args) {
        strStr strStr = new strStr();
        String input = "mississippi";
        String sub = "issipi";
        System.out.println(strStr.strStr(input,sub));
    }

    public int strIndex(String input, String sub){
        int i=0, j=0, k=0,n=input.length(),sn = sub.length();
        if(sn==0)return 0;
        if(n<sn)return -1;
        char[] inArr = input.toCharArray();
        char[] subArr = sub.toCharArray();
        while(j<n){
            while(k<sn && inArr[j]==subArr[k]){
                j++;
                k++;
            }
            if(k==sn) return i;
            else{
                i++;
                k=0;
                j=i;
            }
        }
        return -1;
    }
}
