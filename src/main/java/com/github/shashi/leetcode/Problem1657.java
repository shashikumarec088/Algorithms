package com.github.shashi.leetcode;

import java.util.*;

public class Problem1657 {
    /*
    Determine if Two Strings Are Close

    Two strings are considered close if you can attain one from the other using the following operations:

    Operation 1: Swap any two existing characters.
    For example, abcde -> aecdb
    Operation 2: Transform every occurrence of one existing character into another existing character,
    and do the same with the other character.
    For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
    You can use the operations on either string as many times as necessary.

    Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.



    Example 1:

    Input: word1 = "abc", word2 = "bca"
    Output: true
    Explanation: You can attain word2 from word1 in 2 operations.
    Apply Operation 1: "abc" -> "acb"
    Apply Operation 1: "acb" -> "bca"
    Example 2:

    Input: word1 = "a", word2 = "aa"
    Output: false
    Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
    Example 3:

    Input: word1 = "cabbba", word2 = "abbccc"
    Output: true
    Explanation: You can attain word2 from word1 in 3 operations.
    Apply Operation 1: "cabbba" -> "caabbb"
    Apply Operation 2: "caabbb" -> "baaccc"
    Apply Operation 2: "baaccc" -> "abbccc"


    Constraints:

    1 <= word1.length, word2.length <= 105
    word1 and word2 contain only lowercase English letters.

    Approach 1: using map
    * intuition is to count the frequencies of the characters in both the strings and check if the frequencies are same
    * also check if the characters are same
    algo:
    * create a map of type character and integer and count the frequencies of the characters in both the strings
    * check if keys of both the maps are same
    * init valueslist1 and valueslist2 as values from map1 and map2 respectively
    * sort both the lists
    * check if both the lists are same  if so return true else return false
    time & space:
    * n time(because sorting 26 chars will take const time remaining is n) and n space

    Approach 2: using arrays
    * since we are told all are lower case letters we can use the arrays of size 26 to count
    frequencies
    algo:
    * inint int array arr1 and arr2 of size 26
    * iterate over each word and inc freq at arr1[c- 'a']
    * iterate over both arrays and check if one element is 0 and other is not then return false
    this tells that some character is not present in either of the strings
    * sort both the arrays and check if they are same if so return true else return false
     */

    public boolean closeStrings(String word1, String word2) {
        return closeStringsA1(word1, word2);
    }

    public boolean closeStringsA2(String word1, String word2) {
        int[] counts1 = new int[26];
        int[] counts2 = new int[26];
        for(char c: word1.toCharArray()){
            counts1[c-'a']++;
        }
        for(char c: word2.toCharArray()){
            counts2[c-'a']++;
        }
        for(int i=0;i<26;i++){
            if((counts1[i]==0 && counts2[i]>0) ||
                    (counts2[i]==0 && counts1[i]>0))return false;
        }

        Arrays.sort(counts1);
        Arrays.sort(counts2);
        return Arrays.equals(counts1,counts2);
    }

    public boolean closeStringsA1(String word1, String word2) {
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        for(char c: word1.toCharArray()){
            map1.put(c,map1.getOrDefault(c,0)+1);
        }
        for(char c: word2.toCharArray()){
            map2.put(c,map2.getOrDefault(c,0)+1);
        }
        if(!map1.keySet().equals(map2.keySet()))
            return false;
        List<Integer> list1 = new ArrayList(map1.values());
        List<Integer> list2 = new ArrayList(map2.values());
        Collections.sort(list1);
        Collections.sort(list2);
        return list1.equals(list2);
    }
}
