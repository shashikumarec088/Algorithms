package com.github.shashi.leetcode;
import java.util.*;
 class UnionFind2{
    private int[] root;
    private int[] rank;
    UnionFind2(int n){
        rank = new int[n];
        root = new int[n];
        for(int i=0; i<n; i++){
            rank[i]=1;
            root[i]=i;
        }
    }

    public int find(int x){
        if(root[x] == x)return x;
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
        }
    }

    public boolean isConnected(int x, int y){
        return find(x)==find(y);
    }
}
public class Problem261 {
    public boolean validTree(int n, int[][] edges) {
        return validUF2(n,edges);
    }

    public boolean validUF2(int n, int[][] edges){
        if(edges.length != n-1)return false;
        UnionFind2 uf = new UnionFind2(n);
        for(int[] edge:edges)
            uf.union(edge[0],edge[1]);
        int count = 0;
        for(int i=0; i<n;i++){
            if(uf.find(i)==i) count++;
            if(count>1)return false;
        }
        return true;
    }

    public boolean validDfs2(int n, int[][] edges){
        if(edges.length != n-1) return false;
        Set<Integer>[] graph = new HashSet[n];
        for(int i=0; i<n; i++)
            graph[i] = new HashSet<>();
        for(int[] edge :edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        dfs2(0,seen,graph);
        return seen.size()==n;
    }

    public void dfs2(int i, Set<Integer> seen, Set<Integer>[] graph){
        if(seen.contains(i))return;
        seen.add(i);
        for(int neighbor: graph[i])
            dfs2(neighbor,seen,graph);
        return;
    }

    public boolean validTreeBFS2(int n, int[][] edges){
        if(edges.length != n-1)return false;
        Set<Integer>[] graph = new HashSet[n];
        for(int i=0; i<n; i++)
            graph[i] = new HashSet<>();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        seen.add(0);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int neighbor : graph[node]){
                if(seen.contains(neighbor))continue;
                queue.add(neighbor);
                seen.add(neighbor);
            }
        }
        return seen.size()==n;
    }

    public boolean validTreeStack2(int n, int[][] edges){
        if(edges.length != n-1)return false;
        Set<Integer>[] graph = new HashSet[n];
        for(int i=0; i<n; i++)
            graph[i] = new HashSet<>();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        seen.add(0);
        while(!stack.isEmpty()){
            int node = stack.pop();
            for(int neighbor : graph[node]){
                if(seen.contains(neighbor))continue;
                stack.push(neighbor);
                seen.add(neighbor);
            }
        }
        return seen.size()==n;
    }

    public boolean validTreeDfs(int n, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Set<Integer> seen = new HashSet<>();
        return dfs(0,-1,seen,graph) && seen.size()==n;
    }

    public boolean dfs(int i, int parent, Set<Integer> seen,List<List<Integer>> graph){
        if(seen.contains(i)) return false;
        seen.add(i);
        for(int child : graph.get(i)){
            if(child != parent){
                if(!dfs(child,i,seen,graph)) return false;
            }
        }
        return true;
    }

    public boolean validTreeBFS(int n, int[][] edges){
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i=0; i <n;i++)graph[i]=new HashSet<>();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        Map<Integer,Integer> parent = new HashMap<>();
        parent.put(0,-1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            for(int child : graph[node]){
                if(parent.get(node) == child)continue;
                if(parent.containsKey(child))return false;
                queue.add(child);
                parent.put(child,node);
            }
        }
        return parent.size()==n;
    }

    public boolean validTreeStk(int n, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        Map<Integer,Integer> parent = new HashMap<>();
        parent.put(0,-1);
        while(!stack.isEmpty()){
            Integer node = stack.pop();
            for(int child : graph.get(node)){
                if(parent.get(node) == child )
                    continue;
                if(parent.containsKey(child))
                    return false;
                parent.put(child,node);
                stack.push(child);
            }
        }
        return parent.size()==n;
    }

    public boolean validTreeUF(int n, int[][] edges){
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            if(uf.isConnected(edge[0],edge[1]))
                return false;
            uf.union(edge[0],edge[1]);
        }

        int count = 0;
        for(int i = 0; i<n; i++)
            if(uf.find(i)==i)count++;
        if(count >1)return false;
        return true;
    }
}
class UnionFind{
    private int[] root;
    private int[] rank;
    UnionFind(int n){
        root = new int[n];
        rank = new int[n];
        for(int i =0; i<n; i++){
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x){
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    public void union(int x, int y){
        int rootx = find(x);
        int rooty = find(y);
        if(rootx != rooty){
            if(rank[rootx]>rank[rooty])
                root[rooty]=rootx;
            else if(rank[rooty] > rank[rootx])
                root[rootx] = rooty;
            else{
                root[rooty] = rootx;
                rank[rootx] += 1;
            }
        }
    }

    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
}
