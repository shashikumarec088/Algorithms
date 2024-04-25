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
    public int largestBf(String s){
        int n = s.length(),m=0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(check(s,i,j))
                    m = Math.max(m,j-i+1);
                else break;
            }
        }
        return m;
    }

    public boolean check(String s, int start, int end){
        int[] ch = new int[128];
        for(int i= start; i<= end; i++){
            if(ch[s.charAt(i)]>0) return false;
            ch[s.charAt(i)]++;
        }
        return true;
    }

    /*
        intuition is to use the 2 pointers with hashmap
        to hold the position of characters, when we see the
        repeated charater within the bounds then we update
        the length and the position, remember to update
        the length at the end since when no duplicates
        the ans will not get updated
    */
    public int lengthOfLongestSubstringA1(String s) {
        int i=0, j=0, n = s.length(), l=0;
        Map<Character, Integer> map = new HashMap<>();
        while(i<=j && j<n){
            char c = s.charAt(j);
            if(map.containsKey(c) && map.get(c) >= i){
                l = Math.max(l,j-i);
                i = map.get(c)+1;
            }else map.put(c,j++);
        }
        l =  Math.max(l,j-i);
        return l;
    }
}
