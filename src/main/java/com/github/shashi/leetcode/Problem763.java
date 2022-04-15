package com.github.shashi.leetcode;
import java.util.*;
public class Problem763 {
    public List<Integer> partitionLabels(String s) {
        return partitionLabelsA1(s);
    }

    public List<Integer> partitionLabelsA1(String s){
        int n=s.length(), start=0, stop=0;
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            map.put(c,i);
        }

        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            stop = Math.max(stop, map.get(c));
            if(i==stop){
                result.add(stop-start+1);
                start=i+1;
            }
        }
        return result;
    }
}