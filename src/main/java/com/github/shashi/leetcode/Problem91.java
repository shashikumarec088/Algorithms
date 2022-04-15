package com.github.shashi.leetcode;
import java.util.*;
public class Problem91 {
    public static void main(String[] args) {
        Problem91 problem91 = new Problem91();
        System.out.println(problem91.numDecodings("111111111111111111111111111111111111111111111"));
    }

    public int numDecodings(String s) {
        return numDecodingsA1(s);
    }
    public int numDecodingA4(String s){
        int n = s.length();
        int second = 1;
        int first = s.charAt(0)!='0'?1:0;
        for(int i=2; i<=n; i++){
            int temp = first;
            if(s.charAt(i-1)=='0')first=0;
            int p = Integer.parseInt(s.substring(i-2,i));
            if(p>=10 && p<=26)
                first += second;
            second = temp;
        }
        return first;
    }

    public int numDecodingA3(String s){
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        if(s.charAt(0)!='0')
            dp[1]=1;
        for(int i=2; i<=n; i++){
            if(s.charAt(i-1)!='0')
                dp[i] = dp[i-1];
            int p = Integer.parseInt(s.substring(i-2,i));
            if(p>=10 && p<=26)
                dp[i]+=dp[i-2];
        }
        return dp[n];
    }
    public int numDecodingA2(String s){
        Map<Integer,Integer> memo = new HashMap<>();
        return rec2(s,memo,0);
    }

    public int rec2(String s, Map<Integer,Integer> memo, int start){
        if(memo.containsKey(start))return memo.get(start);
        if(start==s.length())return 1;
        if(s.charAt(start)=='0')return 0;
        if(start==s.length()-1)return 1;
        int count = rec2(s,memo,start+1);
        if(Integer.parseInt(s.substring(start,start+2))<=26)
            count+= rec2(s,memo,start+2);
        memo.put(start,count);
        return count;
    }

    public int numDecodingsA1(String s){
        Map<String,Character> map = new HashMap<>();
        Map<Integer,Integer> memo = new HashMap<>();
        for(int i='A'; i<='Z';i++)
            map.put(""+(i+1-'A'),(char)i);
        return rec(map,s,0,memo);
    }

    public int rec(Map<String,Character> map, String s, int start,
                   Map<Integer,Integer> memo){
        if(start==s.length())
            return 1;
        if(start>s.length()) return 0;
        if(map.containsKey(start))return map.get(start);
        int count=0;
        for(int i=1; i<=2; i++)
            if(start+i <= s.length() &&
                    map.containsKey(s.substring(start,start+i)))
                count+= rec(map,s,start+i,memo);
        memo.put(start,count);
        return count;
    }
}