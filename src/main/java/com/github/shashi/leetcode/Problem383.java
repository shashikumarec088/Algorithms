package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem383 {
    /*
        Ransom Note

        Given two strings ransomNote and magazine, return true if ransomNote can be constructed by
        using the letters from magazine and false otherwise.

        Each letter in magazine can only be used once in ransomNote.

        Example 1:

        Input: ransomNote = "a", magazine = "b"
        Output: false
        Example 2:

        Input: ransomNote = "aa", magazine = "ab"
        Output: false

        Constraints:

        1 <= ransomNote.length, magazine.length <= 105
        ransomNote and magazine consist of lowercase English letters.

        Approach 1:

        * intuition is to construct the map of char and freq for magzine and iterate each char
        from ransom and check if exists and count >0 if not return false
        * if exists reduce the count and proceed to next char
        * return true at the end

     */

    public boolean canConstruct(String ransomNote, String magazine) {
        return canConstructA1(ransomNote,magazine);
    }

    public boolean canConstructA1(String ransomNote, String magazine) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<magazine.length();i++){
            char c = magazine.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        for(int i=0; i<ransomNote.length();i++){
            char c = ransomNote.charAt(i);
            if(map.containsKey(c) && map.get(c)>0){
                map.put(c,map.get(c)-1);
            }else return false;
        }
        return true;
    }


}
