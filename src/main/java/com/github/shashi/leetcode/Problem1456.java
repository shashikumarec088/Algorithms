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
    * init max=0,count=0,n = s.length()
    * define set to check if char is vowel by comparing with aeiou
    * iterate from i=0 to i<k
        * if char is vowel count++
    * init max = count
    * iterate i==k to i<n
        * if char at i is vowel count++;
        * if char at i-k is vowel count--;
        * update max = max(max,count)
    * return max;

    time & space:
    * n time const space
    *
     */

    public int maxVowels(String s, int k) {
        return maxVowelsA1(s,k);
    }


    public int maxVowelsA1(String s, int k) {
        int max=0,count=0,n=s.length();
        Set<Character> vowels = Set.of('a','e','i','o','u');
        for(int i=0; i<k;i++)
            if(vowels.contains(s.charAt(i)))
                count++;
        max = count;
        for(int i=k;i<n;i++){
            if(vowels.contains(s.charAt(i)))count++;
            if(vowels.contains(s.charAt(i-k)))count--;
            max = Math.max(max,count);
        }
        return max;
    }
}
