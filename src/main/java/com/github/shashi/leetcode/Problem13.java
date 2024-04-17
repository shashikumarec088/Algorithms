package com.github.shashi.leetcode;
import java.util.*;
public class Problem13 {

    public int romanToInt(String s) {
        return romanToIntA2(s);
    }

    /*
        basic intuition is to traverse along the string and
        check if current element is smaller than the next element
        if so we consider 2 characters else 1 character for getting
        the integer value for that element
    */
    public int romanToIntA3(String s) {
        int ans=0, n= s.length();
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int i=n-1;
        while(i>=0){
            if(i-1>=0 && map.get(s.charAt(i))> map.get(s.charAt(i-1))){
                ans = ans+ map.get(s.charAt(i)) - map.get(s.charAt(i-1));
                i=i-2;
            }else ans+= map.get(s.charAt(i--));
        }
        return ans;
    }

    public int romanToIntA2(String s) {
        int ans=0, n= s.length();
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int i=0;
        while(i<n){
            if(i+1<n && map.get(s.charAt(i))< map.get(s.charAt(i+1))){
                ans = ans+ map.get(s.charAt(i+1)) - map.get(s.charAt(i));
                i=i+2;
            }else ans+= map.get(s.charAt(i++));
        }
        return ans;
    }

    public int romanToIntA1(String s) {
        int ans=0, prev=0, n= s.length();
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        for(int i=n-1; i>=0; i--){
            int c = map.get(s.charAt(i));
            if(c < prev)ans -=c;
            else ans+=c;
            prev=c;
        }
        return ans;
    }
}