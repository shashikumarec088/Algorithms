package com.github.shashi.leetcode;
import java.util.*;
public class Problem261{
    class UnionFind{
        int[] root,rank;
        UnionFind(int n){
            root = new int[n];
            rank = new int[n];
            for(int i=0; i<n;i++){
                root[i]=i;
                rank[i]=1;
            }
        }

        public int find(int x){
            if(x==root[x])return x;
            return root[x]=find(root[x]);
        }

        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if(rx!=ry){
                if(rank[ry]>rank[rx])
                    root[rx]=ry;
                else if(rank[rx]>rank[ry])
                    root[ry]=rx;
                else{
                    root[rx]=ry;
                    rank[ry]++;
                }
            }
        }

        public boolean isConnected(int x, int y){
            return find(x)==find(y);
        }
    }
    public boolean validTree(int n, int[][] edges) {
        return validTreeA7(n,edges);
    }

    public boolean validTreeA7(int n, int[][] edges){
        if(edges.length != n-1)return false;
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            if(uf.isConnected(edge[0],edge[1]))return false;
            uf.union(edge[0],edge[1]);
        }
        return true;
    }

    public boolean validTreeA6(int n, int[][] edges){
        if(edges.length!=n-1)return false;
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<n; i++)
            adjList.put(i,new ArrayList<>());
        for(int[] edge:edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        seen.add(0);
        while(!stack.isEmpty()){
            int node = stack.pop();
            for(int nei : adjList.get(node)){
                if(seen.contains(nei))continue;
                seen.add(nei);
                stack.add(nei);
            }
        }
        return seen.size()==n;
    }

    public boolean validTreeA5(int n, int[][] edges){
        if(edges.length!=n-1)return false;
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<n; i++)
            adjList.put(i, new ArrayList<>());
        for(int[] edge: edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        dfsA5(0,adjList,seen);
        return seen.size()==n;
    }

    public void dfsA5(int i, Map<Integer,List<Integer>> adjList,Set<Integer> seen){
        if(seen.contains(i))return;
        seen.add(i);
        for(int nei:adjList.get(i))
            dfsA5(nei,adjList,seen);
    }

    public boolean validTreeA4(int n, int[][] edges){
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<n; i++)
            adjList.put(i,new ArrayList<>());
        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        Map<Integer,Integer> parent = new HashMap<>();
        parent.put(0,-1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int nei : adjList.get(node)){
                if(parent.get(node)==nei)continue;
                if(parent.containsKey(nei))return false;
                parent.put(nei,node);
                queue.add(nei);
            }
        }
        return parent.size()==n;
    }

    public boolean validTreeA3(int n, int[][] edges){
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<n; i++)
            adjList.put(i,new ArrayList<>());
        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        return dfsA3(0,adjList,seen,-1) && seen.size()==n;
    }

    public boolean dfsA3(int i, Map<Integer,List<Integer>> adjList,
                         Set<Integer> seen, int parent){
        if(seen.contains(i))return false;
        seen.add(i);
        for(int nei : adjList.get(i)){
            if(nei==parent)continue;
            if(!dfsA3(nei,adjList,seen,i))return false;
        }
        return true;
    }

    public boolean validTreeA2(int n, int[][] edges){
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int i=0; i<n; i++)
            adjList.put(i,new ArrayList<>());
        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        map.put(0,-1);
        while(!stack.isEmpty()){
            int node =stack.pop();
            for(int nei:adjList.get(node)){
                if(map.get(node)==nei)continue;
                if(map.containsKey(nei))return false;
                map.put(nei,node);
                stack.push(nei);
            }
        }
        return n==map.size();
    }

    public boolean validTreeA1(int n, int[][] edges){
        Map<Integer,Set<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges){
            Set<Integer> list = adjList.getOrDefault(edge[0],new HashSet<>());
            list.add(edge[1]);
            adjList.put(edge[0],list);
            Set<Integer> list2 = adjList.getOrDefault(edge[1],new HashSet<>());
            list2.add(edge[0]);
            adjList.put(edge[1],list2);
        }

        Boolean[] visited = new Boolean[n];
        if(dfs21(0,adjList,visited))return false;
        int count=0;
        for(Boolean flag : visited){
            if(flag !=null && flag) count++;
        }
        return count==n;
    }

    public boolean dfs21(int i,Map<Integer,Set<Integer>> adjList,
                         Boolean[] visited ){
        if(visited[i]!=null)return visited[i];
        visited[i]=false;
        for(int nei: adjList.getOrDefault(i,new HashSet<>())){
            if(dfs21(nei,adjList,visited))return true;
        }
        visited[i]=true;
        return false;
    }
}