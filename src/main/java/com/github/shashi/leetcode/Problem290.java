package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem290 {
    /*
    Word Pattern
    Given a pattern and a string s, find if s follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter
    in pattern and a non-empty word in s.

    Example 1:

    Input: pattern = "abba", s = "dog cat cat dog"
    Output: true
    Example 2:

    Input: pattern = "abba", s = "dog cat cat fish"
    Output: false

    "abba" and "dog dog dog dog" -> Expected False

    Constraints:

    1 <= pattern.length <= 300
    pattern contains only lower-case English letters.
    1 <= s.length <= 3000
    s contains only lowercase English letters and spaces ' '.
    s does not contain any leading or trailing spaces.
    All the words in s are separated by a single space.

    Approach 1:
    * intuition is to check if pattern characters are mapping to string in sentence,
    this problem is very similar to isomorphic problem.
    * have a map to maintain char to string mapping at any time if the char is present
    in map and corresponding string and current string in sentence are not same then check is
    failed and it is not the right pattern
    * keep populating the mapping if the character is not found
    * this will fail for case "abba" and "dog dog dog dog" -> Expected False here but above
    approach will  give true, issue is we are checking only one way mapping we need to check
    from string to character as well
    * for that we need to maintain one more map to map string to character to check if string
    is seen before and what is the corresponding character if it is same or different.
    for the above example initially dog is mapped to a but for the 2nd dog we see the pattern
    is b which is different

     */

    public boolean wordPattern(String pattern, String s) {
        return wordPatternA1(pattern,s);
    }

    public boolean wordPatternA1(String pattern, String s) {
        Map<String, Character> map_s_c = new HashMap<>();
        Map<Character,String> map_c_s = new HashMap<>();
        String[] strs = s.split(" ");
        if(strs.length != pattern.length())return false;
        for(int i=0; i<strs.length; i++){
            char c = pattern.charAt(i);
            if(!map_s_c.containsKey(strs[i]))map_s_c.put(strs[i],c);
            if(!map_c_s.containsKey(c))map_c_s.put(c,strs[i]);
            if(map_s_c.get(strs[i]) != c || !map_c_s.get(c).equals(strs[i]))return false;
        }
        return true;
    }
}
