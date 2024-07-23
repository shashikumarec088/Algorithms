package com.github.shashi.leetcode;

public class Problem97 {
    /*
    Interleaving String
    Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
    An interleaving of two strings s and t is a configuration where s and t are divided into n and m
    substrings respectively, such that:
    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
    Note: a + b is the concatenation of strings a and b.
    Example 1:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true
    Explanation: One way to obtain s3 is:
    Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
    Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
    Since s3 can be obtained by interleaving s1 and s2, we return true.
    Example 2:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
    Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
    Example 3:
    Input: s1 = "", s2 = "", s3 = ""
    Output: true
    Constraints:
    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1, s2, and s3 consist of lowercase English letters.
    Follow up: Could you solve it using only O(s2.length) additional memory space?

    Approach 1: recursion TLE
    * intuition is to check all the interleaving combinations and return true if any combination exists,
    we check if cur element in s3 matches with either the cur element of s1 or s2.
    * if cur element matches with that of s1 then we recursively check for remaining part of s3 by considering
    the remaining part of s1 and s2
    * if cur element matches with that of s2 then we recursively check for remaining part of s3 by considering
    the remaining part of s2 and s1
    * here the base case if when we reach the end of the s3 then we consider it as valid interleaving if no
    elements left in both s1 and s2. else we ignore that interleaving
    algo:
    * call recursive function with s1,s2,s3 and 3 pointers i,j,k with values 0 to track the cur positions
    of s1,s2,s3 respectively
    * the base case if when we reach end of s3 ie when k== s3.length then return true if i==s1.length and j==
    s2.length, else false this can be done by just returning s1.length()==i && s2.length()==j
    * then return check if i< s1.length and char at i of s1 is equal to char at k of s3 then recursively call with
    updated values for k and i ie rec(s1,s2,s3,i+1,k+1,j) or check if j<s2.length and char at j== char at k of s3
    then recursively call with updated value of k and j ie rec(s1,s2,s3,i,k+1,j)
    time & space:
    * it takes max m+n space for recursion stack and 2^(m+n) time since there are 2 choices and u need to make
    m+n times

    Approach 2: recursion with memoization with 2d array
    * intuition is same as approach 1 but in approach there are many calculations that are happening
    repeatedly consider example s1=aaa s2=aaaaa s3=aaa
    * if we get the recursion tree
    (0,0,0) -> (1,0,1) ->(2,0,2) -> (3,0,3) false
            |         |->(2,1,3) false
            |->(1,1,2) -> (2,1,3) false
    * if we observe the above recursion tree there are repetitive calculations which can be avoided by
    storing the results in memo array, and checking if the curent positions of i,j are in memo if so
    we return from it else calculate store in memo and return from memo
    algo:
    * create the 2d Boolean array memo of size m+1, n+1 here we need size greater by 1 because at any
    point we consider the s1 and s2 positions at i-1 and j-1
    * base case is same as approach 1
    * check if memo[i][j] is not null if so return from memo
    * init memo[i][j] with the result from recursion calls same as approach 1
    * return what is there at memo[i][j]
    time & space:
    * it takes m*n time since we do not do nay repeatitive calculations and space is m*n for memo

    Approach 3: dp with tabulation using 2d array
    * intuition is same as approach 1 we need to do the bottom up solution building from empty strings
    to complete strings
    * at any position of s1 and s2, we keep comparing the prefixes with the s3 and updating the current
    status based on the previous comparision results and the cur element comparision result
    * here at any position of s1,s2 and check if cur position of s1 matches with that
    of s3 and if the prefix before cur element of s1 and prefix of  till cur position of s2 forms
    the prefix till i+j-1 of s3
    * or the cur position of s2 matches with that of s3 and prefix before cur element of s2 and prefix
    till cur position of s1 forms the prefix till i+j-1 of s3
    * this can be written in terms of recurrance relation as dp[i][j] = (dp[i-1][j] && char at i-1 of s1 == char at
    (i+j-1) or (dp[i][j-1] && char at j-1 of s1 == char at (i+j-1) here i and j represents the prefix lengths
    of s1, s2 so when we are comparing the ith char we need to use i-1 because string chars are 0 based indexes.
    algo:
    * m,n are sizes of s1,s2 check if (m+n) equal to s3 length if not return false
    * create Boolean array of size m+1, n+1 which represents prefixes for s1 and s2 in terms of length
    * iterate from i=0 to <=m and j = 0 to <=n
    * if i and j ==0 then dp[i][j]=ture
    * if i is 0 the dp[i][j] = dp[i][j-1] && char at j of s2 == char at i+j-1 of s3
    * if j=0 then dp[i][j] = d[i-1][j] && char at i of s1 == char at i+j-1 of s3
    * else dp[i][j] = (dp[i-1][j] && char at i of s1 == char at i+j-1 of s3) or
    (dp[i][j-1] && char at j of s2 == char at i+j-1 of s3)
    * return dp[m][n] at the end
    time & space:
    * it takes m*n time and m*n space

    Approach 4:dp with tabulation using 1d array
    * intuition is same as approach 3, if we closely look at the approach at any point we depend only on
    previous row result or the cur row previous col current row result. which can be saved by
    using the 1d array and reuse the same array for the next step
    algo:
    * create the arrray of size of n+1
    * iterate from i=0 to <=m and j=0 to <=n
    * if i and j are 0 then dp[j]=true
    * if i=0 then dp[j]=dp[j-1] && char at j of s2 == char at i+j-1 of s3
    * if j=0 then dp[j] = d[j] && char at i of s1 == char at i+j-1 of s3
    * else dp[j] = (dp[j] && char at i of s1 == char at i+j-1 of s3) or
    (dp[j-1] && char at j of s2 == char at i+j-1 of s3)
    * return dp[n] at the end
    time & space:
    * it takes m*n time and n space
     */

    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleaveA2(s1,s2,s3);
    }

    Boolean[][] memo;

    public boolean isInterleaveA4(String s1, String s2, String s3) {
        int m=s1.length(), n = s2.length();
        if((m+n)!=s3.length())return false;
        boolean[] dp = new boolean[n+1];
        for(int i=0; i<=m;i++){
            for(int j=0; j<=n;j++){
                if(i==0 && j==0)dp[j]=true;
                else if(i==0)dp[j]=dp[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
                else if(j==0)dp[j]=dp[j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                else dp[j]=(dp[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1)) ||
                            (dp[j] && s1.charAt(i-1)==s3.charAt(i+j-1));
            }
        }
        return dp[n];
    }

    public boolean isInterleaveA3(String s1, String s2, String s3) {
        int m=s1.length(), n = s2.length();
        if((m+n)!=s3.length())return false;
        boolean[][] dp = new boolean[m+1][n+1];
        for(int i=0; i<=m;i++){
            for(int j=0; j<=n;j++){
                if(i==0 && j==0)dp[i][j]=true;
                else if(i==0)dp[i][j]=dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
                else if(j==0)dp[i][j]=dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                else dp[i][j]=(dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1)) ||
                            (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    public boolean isInterleaveA2(String s1, String s2, String s3) {
        memo=new Boolean[s1.length()+1][s2.length()+1];
        return rec3(s1,s2,s3,0,0,0);
    }

    public boolean rec3(String s1,String s2, String s3,int i, int j, int k){
        if(k==s3.length())return s1.length()==i && j==s2.length();
        if(memo[i][j]!=null)return memo[i][j];
        memo[i][j] = (i< s1.length() && s1.charAt(i)==s3.charAt(k) && rec3(s1,s2,s3,i+1,j,k+1))
                || (j<s2.length() && s2.charAt(j)==s3.charAt(k) && rec3(s1,s2,s3,i,j+1,k+1));
        return memo[i][j];
    }

    public boolean isInterleaveA1(String s1, String s2, String s3) {
        return rec(s1,s2,s3,0,0,0);
    }

    public boolean rec(String s1,String s2, String s3,int i, int j, int k){
        if(k==s3.length())return s1.length()==i && j==s2.length();
        return (i< s1.length() && s1.charAt(i)==s3.charAt(k) && rec(s1,s2,s3,i+1,j,k+1))
                || (j<s2.length() && s2.charAt(j)==s3.charAt(k) && rec(s1,s2,s3,i,j+1,k+1));
    }
}
