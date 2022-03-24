package com.github.shashi.leetcode;

public class Problem5 {
    public String longestPalindrome(String s) {
        return longestPalindromA3(s);
    }

    public String longestPalindromA3(String s){
        int n=s.length(), st=0,len=1;
        boolean[][] dp = new boolean[n][n];
        for(int i=n-1; i>=0; i--){
            for(int j=i; j<n; j++){
                dp[i][j]=s.charAt(i)==s.charAt(j) &&
                        (j-i<3|| dp[i+1][j-1]);
                if(dp[i][j]&& (j-i+1)>len){
                    len = j-i+1;
                    st = i;
                }
            }
        }
        return s.substring(st,st+len);
    }

    public String longestPalindromeA2(String s){
        //n^2 time
        int n=s.length(), start=0, length=1;
        for(int i=0; i<n; i++){
            // odd length
            int l=i, r=i;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                int curL = r-l+1;
                if(curL>length){
                    start = l;
                    length=curL;
                }
                l--;
                r++;
            }
            // even length input
            l=i;
            r=i+1;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                int curL = r-l+1;
                if(curL>length){
                    start = l;
                    length=curL;
                }
                l--;
                r++;
            }
        }
        return s.substring(start,start+length);
    }

    public String longestPalindromeA1(String s){
        // time limit exceeded
        int n=s.length(), start=0, length=1;
        for(int i=0; i<n; i++)
            for(int j=i+1; j<n; j++)
                if(isPalindrome(s,i,j)){
                    int l = j-i+1;
                    if(l>length){
                        length=l;
                        start=i;
                    }
                }
        return s.substring(start,start+length);
    }

    public boolean isPalindrome(String s, int i, int j){
        while(i<j && s.charAt(i)==s.charAt(j)){
            i++;
            j--;
        }
        return i>=j;
    }
}