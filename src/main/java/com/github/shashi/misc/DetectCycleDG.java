package com.github.shashi.misc;

import java.util.HashSet;

public class DetectCycleDG {
    public static void main(String[] args) {
        DetectCycleDG dc = new DetectCycleDG();
        int[][] grah = new int[][]{
                {1,2},{1,3},{1,4},{1,5},{1,6},
                {2,3},{2,4},{2,5},{2,6},
                {3,4},{3,5},{3,6},
                {4,5},{4,6},
                {5,6}
        };
        dc.solve(6,grah);
    }
    public int solve(int A, int[][] B) {

        HashSet<Integer>[] graph = new HashSet[A+1];
        for(int i = 0; i <= A; i++)
            graph[i] = new HashSet<Integer>();
        for(int[] pair : B){
            graph[pair[0]].add(pair[1]);
        }
        boolean[] visited = new boolean[A+1];
        boolean[] explore = new boolean[A+1];
        for(int i = 1; i <= A; i++){
            if(dfs(graph,visited,explore,i)) return 1;
        }
        return 0;
    }
    public boolean dfs(HashSet<Integer>[] graph,boolean[] visited,boolean[] explore, int i){
        if(visited[i]== true) return false;
        if(explore[i] == true) return true;
        explore[i] = true;
        for(Integer child : graph[i]){
            if(dfs(graph,visited,explore,child)) return true;
        }
        explore[i] = false;
        visited[i] = true;
        return false;
    }
}
