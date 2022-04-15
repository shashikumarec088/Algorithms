package com.github.shashi.leetcode;

public class Problem62 {

    public static void main(String[] args) {
        Problem62 problem62 = new Problem62();
        System.out.println(problem62.uniquePaths(3,3));
    }

    public int uniquePaths(int m, int n) {
        return uniquePathsA1(m,n);
    }

    public int uniquePathsA1(int m, int n){
        Integer[][] memo = new Integer[m][n];
        rec(memo,0,0);
        return memo[0][0];
    }

    public int rec(Integer[][] memo, int sr, int sc){
        if(sr>= memo.length || sc >= memo[0].length)return 0;
        if(sr==memo.length-1 && sc==memo[0].length-1)return 1;
        if(memo[sr][sc]!=null)return memo[sr][sc];
        int count=0;
        count+= rec(memo,sr,sc+1);
        count+= rec(memo,sr+1,sc);
        memo[sr][sc]=count;
        return count;
    }
}