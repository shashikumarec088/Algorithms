package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem1168 {
    public static void main(String[] args) {
        int[] wells = new int[]{1,2,2};
        int[][] pipes = new int[][]{{1,2,1},{2,3,1}};
        Problem1168 problem1168 = new Problem1168();

        int count = problem1168.supplyWaterUf(3,wells,pipes);
    }
    public int supplyWaterUf(int n, int[] wells, int[][] pipes){
        List<int[]> edges = new ArrayList<>();
        for(int i=0; i<n; i++)
            edges.add(new int[]{0,i+1,wells[i]});
        for(int i=0; i<pipes.length; i++)
            edges.add(pipes[i]);
        Collections.sort(edges,(a, b)-> a[2]-b[2]);
        int ans = 0;
        UnionFind1168 uf = new UnionFind1168(n+1);
        for(int i=0; i<edges.size(); i++){
            int[] edg = edges.get(i);
            if(!uf.isConnected(edg[0],edg[1])){
                ans += edg[2];
                uf.union(edg[0],edg[1]);
            }
        }
        return ans;
    }

    class UnionFind1168{
        private int[] root;
        private int[] rank;

        UnionFind1168(int n){
            root = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                root[i]=i;
                rank[i]=1;
            }
        }

        public int find(int x){
            if(root[x]==x) return x;
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
}
