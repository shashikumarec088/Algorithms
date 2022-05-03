package com.github.shashi.leetcode;
import java.util.*;
public class Problem269 {
    public String alienOrder(String[] words) {
        return alienOrderA2(words);
    }

    public String alienOrderA2(String[] words){
        Map<Character,List<Character>> adjList = new HashMap<>();
        int n = words.length;
        for(int i=0; i<n; i++){
            for(char c: words[i].toCharArray())
                adjList.put(c, new ArrayList<>());
        }

        for(int i=0; i<n-1; i++){
            String w1 = words[i];
            String w2 = words[i+1];
            int n1 = w1.length(), n2 = w2.length();
            if(n1>n2 && w1.startsWith(w2))return "";
            for(int j=0; j<Math.min(n1,n2);j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2){
                    adjList.get(c2).add(c1);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Map<Character, Boolean>  visited = new HashMap<>();
        for(char c : adjList.keySet()){
            boolean cycle = dfs(c,visited,sb,adjList);
            if(!cycle)return "";
        }

        if(sb.length() != adjList.size())
            return "";
        return sb.toString();
    }

    public boolean dfs(char c, Map<Character,Boolean> visited,
                       StringBuilder sb, Map<Character,List<Character>> adjList){
        if(visited.containsKey(c))return visited.get(c);
        visited.put(c,false);
        for(char c2 : adjList.get(c)){
            boolean found = dfs(c2,visited,sb,adjList);
            if(!found)return found;
        }
        visited.put(c,true);
        sb.append(c);
        return true;
    }

    public String alienOrderA1(String[] words){
        Map<Character,List<Character>> adjList = new HashMap<>();
        Map<Character,Integer> indegrees = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int n = words.length;

        for(int i=0; i<n; i++){
            for(char c: words[i].toCharArray()){
                adjList.put(c, new ArrayList<>());
                indegrees.put(c,0);
            }
        }

        for(int i=0; i<n-1; i++){
            String w1 = words[i];
            String w2 = words[i+1];

            if(w1.length()>w2.length() &&
                    w1.startsWith(w2))return "";

            for(int j=0; j<Math.min(w1.length(),w2.length());j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2){
                    adjList.get(c1).add(c2);
                    indegrees.put(c2,indegrees.get(c2)+1);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(char c : indegrees.keySet())
            if(indegrees.get(c)==0)queue.add(c);

        while(!queue.isEmpty()){
            char c = queue.poll();
            sb.append(c);
            for(char ac : adjList.get(c)){
                int count = indegrees.get(ac)-1;
                indegrees.put(ac,count);
                if(count==0)queue.add(ac);
            }
        }

        if(sb.length() != indegrees.size())
            return "";

        return sb.toString();
    }
}