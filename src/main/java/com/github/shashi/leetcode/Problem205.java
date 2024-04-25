package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem205 {
    /*
    205. Isomorphic Strings
    Two strings s and t are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the
     order of characters. No two characters may map to the same character,
     but a character may map to itself.

     Example 1:

        Input: s = "egg", t = "add"
        Output: true
        Example 2:

        Input: s = "foo", t = "bar"
        Output: false
        Example 3:

        Input: s = "paper", t = "title"
        Output: true

        Constraints:

        1 <= s.length <= 5 * 104
        t.length == s.length
        s and t consist of any valid ascii character.

        Approach 1:

        * intuition is to check if we replace the same mapping character if there are duplicates
        if the duplicates are there then all should be mapped to same character

        * we can do this by keeping the existing mappings till we processed in map and if repetition
        then check if previous mapping and current element mapping are same else return false;

        * the issue we face with above logic is when s = badc and t= baba if we have one mapping from s to
        it then we proceed with mapping and tell that this is valid but actually for the strings tobe
        isomorphic viceversa also should be true, it mapping from t to s also should be valid which
        is not the case with above t
        * to address this we need to maintain 2 maps and validate on both the mappings

        Approach 2:
        * intuition is that in some way both the strings will be same, if we map both the strings
        to some other string then if they are same then they are isomorphic
        * one of the way is to map to the first occurrence of indexes and compare the values
        * thing to remember is since indices numbers repeat between the characters we should have
        a delimiter like space, this is use full in examples like stenographics and logarithmsxox
        both can be mapped as 123456789110 and 123456789110 but they are not isomorphic this you
        will be able to observe when u seperate each indexes 0 1 2 3 4 5 6 7 8 9 10 11 0 and
        0 1 2 3 4 5 6 7 8 9 10 1 10
        * we should use string for comparision if we use string builder it results in wrong result
        at the end.
     */

    public boolean isIsomorphic(String s, String t) {
        return isIsomorphicA1(s,t);
    }

    public boolean isIsomorphicA1(String s, String t) {
        Map<Character,Character> map_s_t = new HashMap<>();
        Map<Character,Character> map_t_s = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map_s_t.containsKey(c1) && map_s_t.get(c1)!=c2)return false;
            if(map_t_s.containsKey(c2) && map_t_s.get(c2)!=c1)return false;
            map_s_t.put(c1,c2);
            map_t_s.put(c2,c1);
        }
        return true;
    }

    public boolean isIsomorphicA2(String s, String t) {
        Map<Character,Integer> map_s_i = new HashMap<>();
        Map<Character,Integer> map_t_i = new HashMap<>();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(!map_s_i.containsKey(c1))map_s_i.put(c1,i);
            if(!map_t_i.containsKey(c2))map_t_i.put(c2,i);
            sb1.append(map_s_i.get(c1)).append(" ");
            sb2.append(map_t_i.get(c2)).append(" ");
        }
        return sb1.toString().equals(sb2.toString());
    }
}
