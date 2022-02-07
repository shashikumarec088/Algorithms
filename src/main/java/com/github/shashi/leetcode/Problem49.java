package com.github.shashi.leetcode;
import java.util.*;
public class Problem49 {
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
