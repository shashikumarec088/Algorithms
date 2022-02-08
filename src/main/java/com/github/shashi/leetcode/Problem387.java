package com.github.shashi.leetcode;
import java.util.*;
public class Problem387 {

    public static void main(String[] args) {
        Problem387 problem387 = new Problem387();
        System.out.println(problem387.firstUniqChar("aaabbbc"));
    }

    public int firstUniqChar(String s) {
        return firstUniqueA3(s);
    }

    public int firstUniqueA3(String s){
        int n = s.length();
        int[] cache = new int[26];
        for(int i =0; i<n; i++){
            int index = s.charAt(i)-'a';
            if(cache[index]==0)
                cache[index]=i+1;
            else if(cache[index]>0)
                cache[index] = -1;
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<26;i++)
            if(cache[i]>0)
                min = Math.min(min,cache[i]);
        return min==Integer.MAX_VALUE?-1:min-1;
    }

    public int firstUniqueA2(String s){
        int n = s.length();
        int[] cache = new int[26];
        for(int i=0; i<n; i++){
            int index = s.charAt(i) - 'a';
            cache[index]++;
        }
        for(int i=0; i<n; i++){
            int index = s.charAt(i) - 'a';
            if(cache[index]==1) return i;
        }
        return -1;

    }

    public int firstUniqA1(String s){
        int n = s.length();
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<n; i++)
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        for(int i=0; i<n; i++)
            if(map.get(s.charAt(i))==1)
                return i;

        return -1;
    }
}
