package com.github.shashi.leetcode;
import java.util.*;
public class Problem3 {
    public int largestBf(String s){
        int n = s.length(),m=0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(check(s,i,j))
                    m = Math.max(m,j-i+1);
                else break;
            }
        }
        return m;
    }

    public boolean check(String s, int start, int end){
        int[] ch = new int[128];
        for(int i= start; i<= end; i++){
            if(ch[s.charAt(i)]>0) return false;
            ch[s.charAt(i)]++;
        }
        return true;
    }

    public int largest(String s){
        Map<Character,Integer> map = new HashMap<>();
        int i=0, max = 0, n = s.length();
        for(int j=0; j<n; j++){
            char c = s.charAt(j);
            if(map.containsKey(c))
                i = Math.max(map.get(c)+1,i);
            map.put(c,j);
            max = Math.max(max,j-i+1);
        }
        return max;
    }
}
