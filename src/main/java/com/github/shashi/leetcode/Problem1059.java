package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.*;

public class Problem1059 {
    enum State{PROCESSING,PROCESSED}
    public boolean leadToDestColor(int n, int[][] edges, int source, int destination){
        List<Integer>[] graph = buildGraph(n,edges);
        State[] status = new State [n];
        return dfs(graph,status,source,destination);
    }

    public boolean dfs( List<Integer>[] graph, State[] status, int src, int dst){
        if(status[src] != null) return status[src] == State.PROCESSED;
        if(graph[src].isEmpty()) return src == dst;
        status[src] = State.PROCESSING;
        for(int nei : graph[src]){
            if(!dfs(graph,status,nei,dst)) return false;
        }
        status[src] = State.PROCESSED;
        return true;
    }

    public List<Integer>[] buildGraph(int n, int[][] edges){
        List<Integer>[] graph = new List[n];
        for(int i=0; i<n;i++) graph[i] = new ArrayList<>();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
        }
        return graph;
    }
    public static void main(String[] args) {
        Problem1059 problem1059 = new Problem1059();
        int[][]  edges = new int[][]{{5,15},{38,34},{29,5},{6,32},{46,2},{34,22},{2,25},{1,18},{10,10},{26,46},{40,46},{36,19},{16,13},{46,6},{19,32},{7,41},{14,32},{20,13},{0,43},{17,14},{42,41},{40,12},{28,7},{36,35},{18,2},{28,11},{14,32},{4,9},{26,6},{7,17},{49,41},{17,2},{36,34},{18,0},{26,15},{27,10},{26,46},{41,14},{47,19},{19,14},{6,3},{16,14},{21,43},{4,15},{5,2},{31,2},{5,30},{7,33},{18,2},{9,33},{21,44},{1,43},{37,17},{8,24},{21,33},{46,45},{29,14},{22,32},{14,14},{22,32},{42,6},{7,14},{35,13},{36,35},{5,25},{2,3},{23,22},{44,33},{24,13},{35,19},{20,14},{14,32},{35,5},{44,13},{32,32},{32,32},{28,46},{32,32},{37,10},{38,46},{30,30},{0,3},{15,9},{39,15},{42,44},{2,20},{47,0},{49,44},{45,4},{36,22},{13,13},{14,30},{13,14},{31,31},{45,3},{45,5},{34,14},{44,9},{30,30},{40,12},{13,30},{25,20},{34,14},{41,22},{12,34},{5,33},{20,22},{48,5},{48,7},{46,0},{14,32},{32,30},{38,46},{30,30},{35,15},{37,20},{42,2},{26,13},{8,48},{20,30},{37,33},{28,18},{32,30},{10,10},{48,44},{24,14},{8,9},{0,14},{1,43},{14,14},{20,22},{31,10},{1,0},{4,7},{27,41},{41,22},{0,22},{17,19},{8,16},{18,38},{37,23},{5,22},{35,23},{14,30},{30,30},{13,32},{28,23},{24,25},{45,2},{25,22}};
    boolean flag = problem1059.leadsToDestination(50,edges,15,33);
    }
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        return allPaths(n,edges,source,destination);
    }

    public boolean allPaths(int n, int[][] edges, int source, int dest){
        HashSet<Integer>[] graph = new HashSet[n];

        for(int i = 0; i < n ; i++)
            graph[i] = new HashSet<>();
        for(int[] pair : edges){
            graph[pair[0]].add(pair[1]);
        }
        boolean[] visited = new boolean[n];
        boolean[] explore = new boolean[n];
        for(int i = 0; i < n; i++)
            if(!visited[i])
                if(isCyclic(graph,i,visited,explore))
                    return false;

        for(int child : graph[source])
            if(!pathExists(graph, child, dest))
                return false;
        if(graph[source].size() == 0 && edges.length!=0) return false;
        for(int i=0; i <n ; i++)
            if(graph[i].size() == 0 && dest != i) return false;
        return true;
    }

    public boolean isCyclic(HashSet<Integer>[] graph, int src , boolean[] visited, boolean[] explore){
        if(visited[src])return false;
        if(explore[src]) return true;
        explore[src] = true;
        for(int child : graph[src]){
            if(!visited[child])
                if(isCyclic(graph,child,visited,explore))
                    return true;
        }
        explore[src] = false;
        visited[src] = true;
        return false;
    }
    public boolean pathExists(HashSet<Integer>[] graph,int src , int dst){
        if(src == dst) return true;
        for(Integer child : graph[src])
            if(pathExists(graph,child,dst))
                return true;
        return false;
    }
}
