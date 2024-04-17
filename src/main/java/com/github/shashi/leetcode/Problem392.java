package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem392 {
    public boolean isSubsequence(String s, String t) {
        return isSubsequenceA3(s, t);
    }

    /*
        intuition is to build have the maps
        for look up and find out the find index
        for each element greater than previous index
        this helps when we have multiple strings to
        look for
    */
    public boolean isSubsequenceA3(String s, String t) {
        int m = s.length(), n = t.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            char c = t.charAt(i);
            if(!map.containsKey(c))
                map.put(c, new ArrayList<>());
            map.get(c).add(i);
        }

        int index = -1;
        boolean found = false;
        for(int i=0; i<m; i++){
            char c = s.charAt(i);
            if(!map.containsKey(c))return false;
            found = false;
            for(int j : map.get(c)){
                if(index < j){
                    index=j;
                    found=true;
                    break;
                }
            }
            if(!found)return false;
        }
        return true;
    }

    /*
        intuition is to use the devide and conquire
        approach where we check if current char is matching
        if so move forward else move only right pointer
    */
    public boolean isSubsequenceA2(String s, String t) {
        return rec(s, t,0,0,s.length(),t.length());
    }

    public boolean rec(String s, String t, int lb, int rb,
                       int sl, int tl){
        if(lb == sl)return true;
        if(tl == rb)return false;
        if(s.charAt(lb) == t.charAt(rb))
            lb++;
        rb++;
        return rec(s, t, lb, rb, sl, tl);
    }

    /*
        intuition is to search for the matching char
        and increment the pointer when we reach the
        end of 2nd string and 1st is not completed
        the we did not find the sequence else we found
    */
    public boolean isSubsequenceA1(String s, String t) {
        int m = s.length(), n = t.length();
        int i=0,j=0;
        if(m==0)return true;
        if(n==0)return false;
        while(i<m && j<n){
            if(s.charAt(i) == t.charAt(j))i++;
            j++;
        }
        return i==m;
    }
}