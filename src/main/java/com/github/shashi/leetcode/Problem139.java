package com.github.shashi.leetcode;
import java.util.*;
public class Problem139 {

    public static void main(String[] args) {
        String s = "leetcode";
        String[] arr = {};
        Problem139 problem139 = new Problem139();
        LinkedList<Integer> list = new LinkedList<>();
        list.removeLast();
        System.out.println(problem139.wordBreak(s,Arrays.asList("leet","code")));
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakA5(s,wordDict);
    }


    public boolean wordBreakA5(String s, List<String> words){
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n]=true;
        for(int i=n-1; i>=0; i--){
            for(String w : words){
                if(i+w.length() <=n && w.equals(s.substring(i,i+w.length())))
                    dp[i]=dp[i+w.length()];
                if(dp[i])
                    break;
            }
        }
        return dp[0];
    }

    public boolean wordBreakA4(String s, List<String> wordDict){
        int n = s.length();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        Set<String> set = new HashSet<>(wordDict);
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.poll();
            if(visited[start])continue;
            for(int end = start+1; end <= n; end++){
                if(set.contains(s.substring(start,end))){
                    queue.add(end);
                    if(end==n)return true;
                }
            }
            visited[start]=true;
        }
        return false;
    }

    public boolean wordBreakA3(String s, List<String> wordDict){
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++){
            for(int j=0; j<i; j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreakA2(String s, List<String> wordDict){
        return rec2(s, new HashSet<>(wordDict),0, new Boolean[s.length()]);
    }

    public boolean rec2(String s, Set<String> set, int start, Boolean[] memo){
        if(s.length()==start)return true;
        if(memo[start]!=null)return memo[start];
        for(int end=start+1; end<=s.length();end++){
            if(set.contains(s.substring(start,end)) && rec2(s, set, end,memo))
                return memo[start]=true;
        }
        return memo[start]=false;
    }

    public boolean wordBreakA1(String s, List<String> wordDict){
        Set<String> set = new HashSet<>(wordDict);
        return rec(s,set,0);
    }

    public boolean rec(String s, Set<String> set, int start){
        if(s.length()==start)return true;
        for(int end=start+1; end<=s.length(); end++)
            if(set.contains(s.substring(start,end))&& rec(s,set,end))
                return true;
        return false;
    }
}