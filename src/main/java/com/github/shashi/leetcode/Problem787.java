package com.github.shashi.leetcode;


import java.util.*;

class Problem787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return mistCost2(n,flights, src, dst, k);
    }

    public int minCostDfs(int n, int[][] flights, int src, int dst, int k){
        // time complexity n power k and and we get TLE
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] f : flights){
            graph.putIfAbsent(f[0],new ArrayList<>());
            graph.get(f[0]).add(new int[]{f[1],f[2]});
        }
        int[] ans = new int[]{Integer.MAX_VALUE};
        dfs(graph,src,dst,0,k+1,ans);
        return ans[0] != Integer.MAX_VALUE?ans[0]:-1;
    }

    public int minCostBfs(int n, int[][] flights, int src, int dst, int k){
        // time complexity n power k and and we get TLE
        Map<Integer,List<int[]>> graph = new HashMap<>();
        for(int[] f : flights){
            graph.putIfAbsent(f[0],new ArrayList<>());
            graph.get(f[0]).add(new int[]{f[1],f[2]});
        }
        int ans = Integer.MAX_VALUE;
        int steps = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src,0});
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] node = queue.poll();
                if(node[0]==dst){
                    ans = Math.min(node[1],ans);
                    continue;
                }
                if(!graph.containsKey(node[0]))
                    continue;
                for(int[] nei : graph.get(node[0])){
                    if(node[1]+nei[1]>ans)
                        continue;
                    queue.offer(new int[]{nei[0],nei[1]+node[1]});
                }
            }
            if(steps++>k)break;
        }
        return Integer.MAX_VALUE == ans?-1:ans;

    }

    public void dfs(Map<Integer,List<int[]>> graph, int src, int dst,
                    int cost, int k, int[] ans){
        if(k<0) return;
        if(src==dst){
            ans[0]=cost;
            return;
        }
        if(!graph.containsKey(src))return;
        for(int[] nei: graph.get(src)){
            if(cost+nei[1]>ans[0])continue;
            dfs(graph, nei[0],dst,cost+nei[1],k-1,ans);
        }
    }

    public int mistCost2(int n, int[][] flights, int src, int dst, int k){
        if(flights.length==0) return -1;
        int[] prev = new int[n];
        int[] cur = new int[n];
        for(int i=0; i<n; i++){
            prev[i] = Integer.MAX_VALUE;
            cur[i] = Integer.MAX_VALUE;
        }
        prev[src]=0;
        cur[src]=0;
        for(int i=0; i<k+1; i++){
            for(int[] flight : flights){
                int sr = flight[0];
                int ds = flight[1];
                int cost = flight[2];
                if(prev[sr] != Integer.MAX_VALUE){
                    cur[ds] = Math.min(cur[ds],prev[sr]+cost);
                }
            }
            prev = cur.clone();
        }
        return cur[dst] != Integer.MAX_VALUE ? cur[dst]:-1;
    }
}