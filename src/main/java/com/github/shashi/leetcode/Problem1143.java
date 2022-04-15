package com.github.shashi.leetcode;

public class Problem1143 {
    int[][] memo;
    String s1, s2;

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequenceA3(text1, text2);
    }

    public int longestCommonSubsequenceA3(String s1, String s2){
        int m = s1.length(), n = s2.length();
        char[] s1a = s1.toCharArray();
        char[] s2a = s2.toCharArray();
        int[][] dp = new int[m+1][n+1];
        for(int r=m-1; r>=0; r--){
            for(int c=n-1; c>=0; c--){
                if(s1a[r]==s2a[c])
                    dp[r][c] = 1+dp[r+1][c+1];
                else dp[r][c] = Math.max(dp[r+1][c],dp[r][c+1]);
            }
        }
        return dp[0][0];
    }

    public int longestCommonSubSequenceA2(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
        this.memo = new int[s1.length()+1][s2.length()+1];
        for(int i=0; i<s1.length();i++)
            for(int j=0; j<s2.length(); j++)
                memo[i][j]=-1;
        return memo2(0,0);
    }

    public int memo2(int p1, int p2){
        if(memo[p1][p2]!=-1)return memo[p1][p2];
        int ans=0;
        if(s1.charAt(p1)==s2.charAt(p2))
            ans = 1+ memo2(p1+1,p2+1);
        else ans=Math.max(memo2(p1+1,p2),memo2(p1,p2+1));
        return memo[p1][p2] = ans;
    }

    public int longestCommonSubSequenceA1(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;

        this.memo = new int[s1.length()+1][s2.length()+1];
        for(int i=0; i<s1.length(); i++)
            for(int j=0; j<s2.length(); j++)
                memo[i][j]=-1;
        return memor(0,0);
    }

    public int memor(int t1, int t2){
        if(memo[t1][t2]!=-1)return memo[t1][t2];
        int fo = s2.indexOf(s1.charAt(t1),t2);
        int op1 = memor(t1+1,t2);
        int op2 = 0;
        if(fo!=-1)
            op2 = 1+memor(t1+1,fo+1);
        return memo[t1][t2] = Math.max(op1,op2);
    }
}