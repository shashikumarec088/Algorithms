package com.github.shashi.leetcode;
import java.util.*;
public class Problem3 {
    /*
        3. Longest Substring Without Repeating Characters

        Given a string s, find the length of the longest substring without repeating characters.

        Example 1:

        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.
        Example 2:

        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.

        Constraints:

        0 <= s.length <= 5 * 104
        s consists of English letters, digits, symbols and spaces.

        Approach 1:

        * intuition is to use the 2 pointers 1 slow moving pointer and one fast pointer
        and maintain map for character to position, when we check if the char is present
        in the map
        *  check if character position is > slow pointer then this is the first duplicate,
        then calculate the length and update if greater than previous length
        * update the char position to latest and increment slow pointer to old position plus 1
        * we need to remember to update the length at the end which covers the case for all the
        non overlapping characters
     */
    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstringA1(s);
    }

    public int lengthOfLongestSubstringA1(String s) {
        int i=0,j=0,maxLen=0,n=s.length();
        Map<Character,Integer> map = new HashMap<>();
        while(j<n){
            char c = s.charAt(j);
            if(map.containsKey(c) && map.get(c)>=i){
                maxLen = Math.max(j-i,maxLen);
                i= map.get(c)+1;
            }
            map.put(c,j);
            j++;
        }
        return Math.max(j-i,maxLen);
    }
}
