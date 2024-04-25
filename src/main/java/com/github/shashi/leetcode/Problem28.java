package com.github.shashi.leetcode;

public class Problem28 {
    /*
    Find the Index of the First Occurrence in a String
    Given two strings needle and haystack, return the index of the first occurrence of needle in
    haystack, or -1 if needle is not part of haystack.

    Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

    Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.

    Constraints:
    1 <= haystack.length, needle.length <= 104
    haystack and needle consist of only lowercase English characters.

    approach 1:
    * intuition is to try matching all the substrings from left of haystack and return the index
    when we find the first match else return -1
    algo:
    * iterate over all the haystack from 0 to <= m-n since from m-n onwards the substring length will
    be smaller than needle so we no need to match.
    * in the inner loop start from each index and check for next n characters if all matches return i;
    else break the loop and start for the next index
    space & time:
    * time complexity is o(mn) since we loop for each index and we are not using any extra space
     */
    public int strStr(String haystack, String needle) {
        return strStrA1(haystack,needle);
    }

    public int strStrA1(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        for(int i=0; i<=m-n; i++){
            int k=0;
            for(int j=i; j<m; j++){
                if(haystack.charAt(j) != needle.charAt(k))break;
                k++;
                if(k==n)return i;
            }
        }
        return -1;
    }
}