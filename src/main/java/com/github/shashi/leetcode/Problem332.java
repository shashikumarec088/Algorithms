package com.github.shashi.leetcode;
import java.util.*;
public class Problem332 {

    private Map<String,List<String>> graph;
    private Map<String,boolean[]> visited;
    private List<String> result;
    public List<String> itenarary(List<List<String>> tickets){
        graph = new HashMap<>();
        visited = new HashMap<>();
        for(List<String> ticket : tickets){
            String src = ticket.get(0);
            String dst = ticket.get(1);
            graph.putIfAbsent(src,new LinkedList<>());
            graph.get(src).add(dst);
        }
        for(String key : graph.keySet()){
            List<String> nei = graph.get(key);
            Collections.sort(nei);
            visited.put(key,new boolean[nei.size()]);
        }
        LinkedList<String> list = new LinkedList<>();
        list.add("JFK");
        dfs(list,"JFK",tickets.size()+1);
        return result;
    }

    public boolean dfs(LinkedList<String> list,String src, int n){
        if(list.size()==n){
            result = new ArrayList<>(list);
            return true;
        }

        if(!graph.containsKey(src)) return false;

        int i = 0;
        boolean[] bits = visited.get(src);
        for(String dst : graph.get(src)){
            if(!bits[i]){
                list.add(dst);
                bits[i]=true;
                boolean found = dfs(list,dst,n);
                list.removeLast();
                bits[i]=false;
                if(found) return true;
            }
            i++;
        }
        return false;
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        return itenarary(tickets);
    }
}
