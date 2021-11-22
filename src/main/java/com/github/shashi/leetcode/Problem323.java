package com.github.shashi.leetcode;
import java.util.*;
class Problem323 {
    public int countComponents(int n, int[][] edges) {
        return ccDfs( n, edges);
    }
    public static void main(String[] args) {
        Problem323 uf = new Problem323();
        int[][] edges = new int[][]{{0,3},{1,2},{0,2}};
        String input = "dcab";
        int out = uf.ccUf(4,edges);
        System.out.println(out);
    }
    public int ccUf(int n, int[][] edges){
        UnionFind323 uf = new UnionFind323(n);
        for(int[] edge : edges){
            uf.union(edge[0],edge[1]);
        }
        return uf.getComponents();
    }

    public int ccDfs(int n, int[][] edges){
        Set<Integer>[] graph = new HashSet[n];
        for(int i=0; i<n; i++)graph[i]= new HashSet<>();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int count=0;
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i,visited,graph);
                count++;
            }
        }
        return count;
    }

    public void dfs(int i, boolean[] visited, Set<Integer>[] graph){
        if(visited[i]) return;
        visited[i]=true;
        for(int neighbor: graph[i])
            dfs(neighbor,visited,graph);
        return;
    }
}
class UnionFind323{
    private int[] root;
    private int[] rank;
    private int count;

    UnionFind323(int n){
        root = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            root[i]=i;
            rank[i]=1;
        }
        count = n;
    }

    public int find(int x){
        if(root[x]==x)return x;
        return root[x]=find(root[x]);
    }

    public void union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx != ry){
            if(rank[rx]>rank[ry])
                root[ry]=rx;
            else if(rank[ry]>rank[rx])
                root[rx]=ry;
            else{
                root[ry]=rx;
                rank[rx]+=1;
            }
            count--;
        }
    }

    public boolean isConnected(int x, int y){
        return find(x)==find(y);
    }

    public int getComponents(){
        return count;
    }
}
