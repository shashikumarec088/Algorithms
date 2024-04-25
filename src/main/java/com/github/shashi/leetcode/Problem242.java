package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem242 {
    /*
        Valid Anagram
        Given two strings s and t, return true if t is an anagram of s, and false otherwise.

        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
        typically using all the original letters exactly once.

        Example 1:

        Input: s = "anagram", t = "nagaram"
        Output: true
        Example 2:

        Input: s = "rat", t = "car"
        Output: false

        Constraints:

        1 <= s.length, t.length <= 5 * 104
        s and t consist of lowercase English letters.


        Follow up: What if the inputs contain Unicode characters? How would you adapt your solution
         to such a case?

        Approach 1:
        * intuition is to use the map and build the frequency map for 1st string and check
          for each char in 2nd string present in 1st and freq is greater then 0 if so update freq
        * note strings length should be same else they are not anagrams
        * have map to hold char and freq iterate over each char check if not present or freq is 0
        if so return false else decrease freq, return true at the end
        * to handle the follow up case if we use the hashMap then it will handle the unicodes, if the
        characters are only alphabets we can just use int array of size 26 and use c - 'a' as indices
        to maintain the count but it does not handle special characters.

        Approach 2:
        * same as approach 1 instead of using map we can use int array counter with size 26 as characters
        are 26

     */

    public boolean isAnagram(String s, String t) {
        return isAnagramA1(s,t);
    }

    public boolean isAnagramA1(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        if(s.length() != t.length())return false;
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            if(!map.containsKey(c) || map.get(c)==0)return false;
            map.put(c,map.get(c)-1);
        }
        return true;

    }

    public boolean isAnagramA2(String s, String t) {
        int[] map = new int[26];
        if(s.length() != t.length())return false;
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            map[c - 'a']++;
        }

        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            if(map[c - 'a']==0)return false;
            map[c - 'a']--;
        }
        return true;

    }
}
