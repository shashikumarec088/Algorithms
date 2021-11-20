package com.github.shashi.leetcode;

class QuickUnionFindRank{
    int[] root;
    int[] height;
    QuickUnionFindRank(int size){
        this.root = new int[size];
        for(int i = 0; i < size; i++){
            root[i] = i;
            height[i] =1;
        }
    }

    public int find(int x){
        while (root[x] != x)
            x = root[x];
        return x;
    }

    public boolean areConnected(int x, int y){
        return find(x) == find(y);
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
           if(height[rootX]>height[rootY])
               root[rootY] = rootX;
           else if(height[rootX] < height[rootY])
               root[rootX] = rootY;
           else{
               root[rootY] = rootX;
               root[rootX] += 1;
           }
        }
    }
}

public class QuickUnionFindRankApp {
}
