package com.github.shashi.leetcode;

import java.util.Set;

public class Problem1456 {
    /*
    1456. Maximum Number of Vowels in a Substring of Given Length

    Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

    Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.



    Example 1:

    Input: s = "abciiidef", k = 3
    Output: 3
    Explanation: The substring "iii" contains 3 vowel letters.
    Example 2:

    Input: s = "aeiou", k = 2
    Output: 2
    Explanation: Any substring of length 2 contains 2 vowels.
    Example 3:

    Input: s = "leetcode", k = 3
    Output: 2
    Explanation: "lee", "eet" and "ode" contain 2 vowels.


    Constraints:

    1 <= s.length <= 105
    s consists of lowercase English letters.
    1 <= k <= s.length

    Approach 1: sliding window sum
    * intuition is to count first k vowels and then update max and count based on liding window from k
    algo:
    * have the set of ovel chars
    * max = 0, count=0, i=0,j=0
    * iterate j to n
    * if char is ovel count++
    * if((j-i+1)>k){
        if(char at i is ovel)count--
        i++
    }
    max = max(max,count)

    time & space:
    * n time const space
    *
     */

    public int maxVowels(String s, int k) {
        return maxVowelsA1(s,k);
    }


    public int maxVowelsA1(String s, int k) {
        Set<Character> vowels = Set.of('a','e','i','o','u');
        int max=0,i=0,count=0;
        for(int j=0; j<s.length();j++){
            if(vowels.contains(s.charAt(j)))count++;
            if((j-i+1)>k){
                if(vowels.contains(s.charAt(i)))count--;
                i++;
            }
            max = Math.max(max,count);
        }
        return max;
    }
}
