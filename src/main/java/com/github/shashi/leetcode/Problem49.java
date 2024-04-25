package com.github.shashi.leetcode;
import java.util.*;
public class Problem49 {
    /*
    Group Anagrams

    Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.

    Example 1:
    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

    Example 2:
    Input: strs = [""]
    Output: [[""]]

    Example 3:
    Input: strs = ["a"]
    Output: [["a"]]

    Constraints:
    1 <= strs.length <= 104
    0 <= strs[i].length <= 100
    strs[i] consists of lowercase English letters.

    Approach 1:
    * intuition is to sort each string and create a map of string, list, with sorted string as key
     add the actual string to list. collect all the lists at the end
    algo :
    * create the hash map of string, list. iterate over all the strings in array, get the char array
    for each string and create the new string using the char array.
    * check if sorted string is present in map if not add new list to that string and add the actual
    element to the list
    * at the end collect all the values in map and return
    time & space :
    * time is m nlogn where n is each string length and m is number of strings in array
    * space complexity is 0(m * n) since there are m strings and each of length n

    Approach 2:
    * intuition is to use the array to store the char freq within each string which will make the
    sorting time complexity o(n) and build the key out the counted freq and rest is same as approach1
    algo:
    *  same as approach one create a hashmap of string, list of strings. for each string in array
    * create an int array of size 26 since there are 26 alphabets and inc count at c-'a'
    * once we have freq create a key by concatinating each index freq seperated by # delimilar
    * similar to approach 1 create an list in map if not present then add the current actual string
    * return all values at the end
    time & space:
    * time complexity will be mk where m is number of strings and each has k characters
    * space is o( mn) since m strings are there each with n characters


     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return groups(strs);
    }

    public List<List<String>> groups(String[] strs){
        Map<String,List<String>> map = new HashMap<>();
        for(String str : strs){
            int[] counts = new int[26];
            for(char c : str.toCharArray())
                counts[c-'a']++;
            StringBuilder sb = new StringBuilder();
            for(int count : counts){
                sb.append('#');
                sb.append(count);
            }
            String s2 = sb.toString();
            map.putIfAbsent(s2,new ArrayList<>());
            map.get(s2).add(str);

        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupsAnagramsSol(String[] strs){
        Map<String,List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String sStr = new String(strArr);
            map.putIfAbsent(sStr, new ArrayList<>());
            map.get(sStr).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
