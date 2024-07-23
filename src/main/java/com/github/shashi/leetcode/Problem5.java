package com.github.shashi.leetcode;

public class Problem5 {
    /*
    Longest Palindromic Substring
    Given a string s, return the longest
    palindromic substring in s.
    Example 1:
    Input: s = "babad"
    Output: "bab"
    Explanation: "aba" is also a valid answer.
    Example 2:
    Input: s = "cbbd"
    Output: "bb"
    Constraints:
    1 <= s.length <= 1000
    s consist of only digits and English letters.

    Approach 1: bruteforce
    * intuition is to generate all the substrings and check if they are palindromes, we can start generating
    substrings from length n to 1 so that whenever if find palindrome then that will be the largest 1.
    * start from length n generate all the substrings of length n then check if palindrome if so return substring
    * repeat it until the length becomes 1
    algo:
    * interate from i=n to <=1 and j=0 to j<= n-i
    * check if palindrome by passing s, j, i-1 (because i is length but palindrome expects end index)
    * if palindrome then return substring from j to i+j
    * in palindrome we take s, i,j
    * we iterate until i<j if char at i != char at j then return false
    * inc i, dec j
    * return true at the end.
    time & space:
    * takes n^3 space and const time

    Approach 2: dp
    * intuition is to avoid the duplicate effort spent on checking if palindrome or not for each substring
    * we store the result of already processed substring and reusing that result when computing the palindrome
    check for bigger string
    * for any substring between (i,j) the palindrome check will be to check if char at i,j are same and (i+1,j-1)
    is palindrome
    * if process the string from end to start position and from that position till end of string if we compute
    the result and store in dp array then it can be reused, for example if we know 4,4 then 3,5 is dependent
    on 4,4 but when difference is 1 then we no need to depend at there will be 1 or 2 chars
    * this give the relation dp[i][j] = char at i== char at j and difference j-i < 2 or dp[i+1][j-1]
    algo:
    * init dp of size n,n of type boolean and also start=0 and end=0 to store the max palindrome substring
    indexes
    * iterate from i=n-1 to i>=0 dec i and j=i to j<n inc j
    * check if char at i== char at j and either j-i difference is <2 or dp[i+1][j-1] is true
    * then set dp[i][j] to true and also update start and end if j-i > end-start
    * return substring from start to end at the end.
    time & space:
    * it takes n^2 time and n^2 space to store the dp solutions

    Approach 3: expand from center
    * intuition is when we consider boundaries for each at each position then their will be n^2 boundaries
    but if we consider centers then their will be n centers which will help reduce the time complexity to
    n^2 overall without using the extra space
    * for each position the substring can expand in both the directions within the boundary of the string
    we can keep expanding till the left and right chars are same and keep updating the max substring
    * to consider both the odd and even length substrings we need to expand twice once for add substrings
    by considering single center ir keep l=i and r=i and expand and update result and once by considering
    l=i, r=i+1 and keep expanding and updating the result this will cover both the cases like aba and anna
    algo:
    * init n = s.length, start=0, end=0
    * iterate i=0 to n inc i
    * consider l=i and r=i for add substrings
    * iterate until l>=0 and r<n and char at l and r are same then  update start and end if r-l > end-start
    * dec l inc r
    * reset l=i, r=i+1 fpr even substrings
    * iterate until l>=0 and r<n and char at l and r are same then  update start and end if r-l > end-start
    * dec l inc r
    * return substring from start to end+1 at then (end is plus one because end index is excluded in java)
    time & space:
    * it takes n^2 time and const space
     */
    public String longestPalindrome(String s) {
        return longestPalindromeA3(s);
    }

    public String longestPalindromeA3(String s) {
        int n=s.length(),start=0,end=0;
        for(int i=0; i<n;i++){
            int l=i,r=i;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                if((r-l)>(end-start)){
                    start=l;
                    end=r;
                }
                l--;
                r++;
            }
            l=i;
            r=i+1;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                if((r-l)>(end-start)){
                    start=l;
                    end=r;
                }
                l--;
                r++;
            }
        }
        return s.substring(start,end+1);
    }

    public String longestPalindromeA2(String s) {
        int n=s.length(),start=0,end=0;
        boolean[][] dp = new boolean [n][n];
        for(int i=n-1; i>=0;i--){
            for(int j=i; j<n;j++){
                if((s.charAt(i)==s.charAt(j)) &&
                        ((j-i)<2 || dp[i+1][j-1])){
                    dp[i][j]=true;
                    if((j-i)>(end-start)){
                        start=i;
                        end=j;
                    }
                }
            }
        }
        return s.substring(start,end+1);
    }

    public String longestPalindromeA1(String s) {
        int n=s.length();
        for(int i=n; i>0;i--){
            for(int j=0; j<=n-i;j++){
                if(isPalindrome(s,j,i+j-1))
                    return s.substring(j,j+i);
            }
        }
        return "";
    }

    public boolean isPalindrome(String str, int s, int e) {
        while(s<e){
            if(str.charAt(s)!=str.charAt(e))return false;
            s++;
            e--;
        }
        return true;
    }
}