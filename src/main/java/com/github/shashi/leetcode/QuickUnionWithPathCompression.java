package com.github.shashi.leetcode;

public class QuickUnionWithPathCompression {
    int[] root;
    QuickUnionWithPathCompression(int size){
        this.root = new int[size];
        for(int i = 0; i < size; i++)
            this.root[i] = i;
    }

    public int find(int x){
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
            root[rootY] = rootX;
    }

    public boolean connected(int x, int y){
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        QuickUnionWithPathCompression qup = new QuickUnionWithPathCompression(10);
        // 1-2-5-6-7 3-8-9 4
        qup.union(1,2);
        qup.union(2,5);
        qup.union(5,6);
        qup.union(6,7);
        qup.union(3,8);
        qup.union(8,9);
        System.out.println(qup.connected(1,5));
        System.out.println(qup.connected(5,7));
    }
}
