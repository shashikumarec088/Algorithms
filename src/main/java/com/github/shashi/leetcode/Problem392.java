package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem392 {
    /*
    Is Subsequence
    Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

    A subsequence of a string is a new string that is formed from the original string by deleting
    some (can be none) of the characters without disturbing the relative positions of the remaining
    characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

    Example 1:

    Input: s = "abc", t = "ahbgdc"
    Output: true
    Example 2:

    Input: s = "axc", t = "ahbgdc"
    Output: false

    Constraints:

    0 <= s.length <= 100
    0 <= t.length <= 104
    s and t consist only of lowercase English letters.

    Follow up: Suppose there are lots of incoming s, say s1, s2, ...,
    sk where k >= 109, and you want to check one by one to see if t has its subsequence.
    In this scenario, how would you change your code?

    Approach 1:
    * intuition is to use the two pointer technique, have i=0 and j=0 iterate until either
    not reaches end
    * if char is same inc i else only inc j
    * at the end if i is equal to string length then return true else false

    Approach 2:
    * intuition is to enhance the approach 1 to handle the multiple source strings,
    this can be handled by having the map to hold the char and its frequency for the target string

    * then for each char at the source check if present in target and get the index which is greater
    than the previous index, we need to store the prev index as we should ignore the indexes below that

    * if index is not found greater than prev then we did not find the char hence we should return false

    * time complexity for this approach is o(n) + m * n (if we use linear search) or m log n if
    we use binary search to find the index for the given char

    Approach 3:
    * intuition is same as approach 1 but instead of iterations we can use the recursion
    * we can pass the starting indexes and s and t when sindex is equal to length return true;
    if tindex = n return false, if char at s is equal to t inc s, inc t call method with updated values
     */
    public boolean isSubsequence(String s, String t) {
        return isSubsequenceA3(s, t);
    }

    public boolean isSubsequenceA2(String s, String t) {
        int m = s.length(), n = t.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            char c = t.charAt(i);
            if(!map.containsKey(c))
                map.put(c, new ArrayList<>());
            map.get(c).add(i);
        }

        int index = -1;
        boolean found = false;
        for(int i=0; i<m; i++){
            char c = s.charAt(i);
            if(!map.containsKey(c))return false;
            found = false;
            for(int j : map.get(c)){
                if(index < j){
                    index=j;
                    found=true;
                    break;
                }
            }
            if(!found)return false;
        }
        return true;
    }

    public boolean isSubsequenceA3(String s, String t) {
        return rec(s,t,0,0);
    }

    public boolean rec(String s, String t, int si, int ti){
        if(si==s.length())return true;
        if(ti==t.length())return false;
        if(s.charAt(si)==t.charAt(ti))si++;
        ti++;
        return rec(s,t,si,ti);
    }

    public boolean isSubsequenceA1(String s, String t) {
        int m = s.length(), n = t.length();
        int i=0,j=0;
        if(m==0)return true;
        if(n==0)return false;
        while(i<m && j<n){
            if(s.charAt(i) == t.charAt(j))i++;
            j++;
        }
        return i==m;
    }
}