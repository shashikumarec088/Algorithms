package com.github.shashi.leetcode;

public class Problem1768 {
    /*
    You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

    Return the merged string.



    Example 1:

    Input: word1 = "abc", word2 = "pqr"
    Output: "apbqcr"
    Explanation: The merged string will be merged as so:
    word1:  a   b   c
    word2:    p   q   r
    merged: a p b q c r
    Example 2:

    Input: word1 = "ab", word2 = "pqrs"
    Output: "apbqrs"
    Explanation: Notice that as word2 is longer, "rs" is appended to the end.
    word1:  a   b
    word2:    p   q   r   s
    merged: a p b q   r   s
    Example 3:

    Input: word1 = "abcd", word2 = "pq"
    Output: "apbqcd"
    Explanation: Notice that as word1 is longer, "cd" is appended to the end.
    word1:  a   b   c   d
    word2:    p   q
    merged: a p b q c   d


    Constraints:

    1 <= word1.length, word2.length <= 100
    word1 and word2 consist of lowercase English letters.

    Approach 1:

    intuition:
        * since we are asked to merge the 2 strings we can use the 2 pointer technique and keep
          adding the characters to result string

    algorithm:
        * initialize m as word1 length and n as word2 length
        * initialize i=0 and j=0, also initialize StringBuilder to sb to store the result
        * iterate until i<m and j<n
        * if i<m append character at word1[i] to sb similarly if j<n append character at
          word2[j] to sb and inc i and j
        * return sb.tostring() at the end

    time & space:
        * it takes Max(m,n) time to build the StringBuilder and O(m+n) to get String
          so the overall time complexity is O(m+n)
        * space complexity is O(m+n) to store the result if we ignore result then it is constant

    Approach 2:

    intuition:
        * intuition is same as approach 1 instead of using the 2 pointers we can use the 1 pointer
          rest of the logic remains the same.

    algorithm:
        * initialize m as word1 length and n as word2 length
        * initialize i=0 and l=max(m,n), also initialize StringBuilder to sb to store the result
        * iterate until i<l
        * if i<m append character at word1[i] to sb similarly if i<n append character at
          word2[i] to sb and inc i
        * return sb.tostring() at the end

    time & space:
        * it takes Max(m,n) time to build the StringBuilder and O(m+n) to get String
          so the overall time complexity is O(m+n)
        * space complexity is O(m+n) to store the result if we ignore result then it is constant

     */

    public String mergeAlternately(String word1, String word2) {
        return mergeAlternatelyA2(word1,word2);
    }

    public String mergeAlternatelyA1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i=0, j=0;
        StringBuilder sb = new StringBuilder();
        while(i<m || j<n){
            if(i<m)sb.append(word1.charAt(i++));
            if(j<n)sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    public String mergeAlternatelyA2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i=0,l = Math.max(m,n);
        StringBuilder sb = new StringBuilder();
        while(i<l){
            if(i<m)sb.append(word1.charAt(i));
            if(i<n)sb.append(word2.charAt(i));
            i++;
        }
        return sb.toString();
    }

}
