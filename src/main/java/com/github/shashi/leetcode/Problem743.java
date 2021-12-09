package com.github.shashi.leetcode;

import java.util.List;
import java.util.*;

class Problem743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        return getDelayDfs(times,n,k);
    }

    public int getDelayDfs(int[][] times, int n, int k){
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i=1; i<n+1; i++)
            graph.put(i, new ArrayList<>());
        for(int[] edge : times)
            graph.get(edge[0]).add(new int[]{edge[2],edge[1]});
        Map<Integer,Integer> dist = new HashMap<>();
        for(int i=1; i<n+1; i++)
            dist.put(i, Integer.MAX_VALUE);
        for(int i =1 ; i < n+1;i++)
            Collections.sort(graph.get(i),(a,b) -> a[0]-b[0]);
        dfs(graph,dist,k,0);
        int ans =0;
        for(Integer key : dist.keySet()){
            if(dist.get(key)==Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans,dist.get(key));
        }
        return ans;
    }

    public void dfs(Map<Integer,List<int[]>> graph,Map<Integer,Integer> dist,
                    int k, int sum){
        if(sum >= dist.get(k)) return;
        dist.put(k,sum);
        if(graph.containsKey(k))
            for(int[] edge : graph.get(k))
                dfs(graph,dist,edge[1],sum+edge[0]);
    }

    public int getDelay(int[][] times, int n, int k){
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int i=1; i<n+1; i++)
            graph.put(i,new ArrayList<>());
        for(int[] edge : times)
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);
        pq.offer(new int[]{k,0});
        Map<Integer,Integer> dist= new HashMap<>();
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            if(dist.containsKey(node[0]))continue;
            dist.put(node[0],node[1]);
            for(int[] nei : graph.get(node[0])){
                if(!dist.containsKey(nei[0])){
                    pq.offer(new int[]{nei[0],nei[1]+node[1]});
                }
            }
        }
        if(dist.size() < n) return -1;
        int ans = 0;
        for(Integer key : dist.keySet())
            ans = Math.max(ans,dist.get(key));
        return ans;
    }
}