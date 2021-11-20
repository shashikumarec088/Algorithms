package com.github.shashi.leetcode;

class QuickUnionFind{
    int[] root;
    QuickUnionFind(int size){
        this.root = new int[size];
        for(int i = 0; i < size; i++)
            root[i] = i;
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
            root[rootY] = rootX;
        }
    }
}
public class QuickUnionFindApp {
}
