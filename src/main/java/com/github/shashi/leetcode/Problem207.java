package com.github.shashi.leetcode;
import java.util.*;
public class Problem207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return canFinishA2(numCourses,prerequisites);
    }
    boolean[] visitedOuter;

    public boolean canFinishA2(int numCourses, int[][] prerequisites){
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        visitedOuter = new boolean[numCourses];
        for(int[] edge : prerequisites){
            List<Integer> list = adjList.getOrDefault(edge[1],new ArrayList<>());
            list.add(edge[0]);
            adjList.put(edge[1],list);
        }
        boolean[] visited = new boolean[numCourses];
        for(int i=0; i<numCourses; i++)
            if(backTrack(i,adjList,visited))return false;
        return true;
    }

    public boolean backTrack(int i, Map<Integer,List<Integer>> adjList, boolean[] visited){
        if(visitedOuter[i])return false;
        if(visited[i])return true;
        if(!adjList.containsKey(i))return false;
        visited[i]=true;

        boolean ret=false;
        for(Integer nei : adjList.get(i)){
            ret = backTrack(nei,adjList,visited);
            if(ret)break;
        }
        visited[i]=false;
        visitedOuter[i]=true;
        return ret;
    }

    public boolean canFinishA1(int n, int[][] prereq){
        List<Integer>[] adj = new ArrayList[n];
        visitedOuter = new boolean[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int[] edj : prereq)
            adj[edj[0]].add(edj[1]);
        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[n];
            if(dfs(i,adj,visited)) return false;
        }
        return true;
    }

    public boolean dfs(int i, List<Integer>[] adj, boolean[] visited){
        if(visitedOuter[i])return false;
        if(visited[i]) return true;
        visited[i]=true;
        for(int nei : adj[i]){
            if(dfs(nei,adj,visited)) return true;
        }
        visited[i]=false;
        visitedOuter[i]=true;
        return false;
    }
}
