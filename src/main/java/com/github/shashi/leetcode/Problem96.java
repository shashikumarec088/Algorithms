package com.github.shashi.leetcode;

public class Problem96 {

    public static void main(String[] args) {
        Problem96 problem119 = new Problem96();
        System.out.println(problem119.numTrees(3));
    }

    public int numTrees(int n) {
        return getNumTrees(n);
    }

    public int getNumTrees(int n){
        int[] G = new int[n+1];
        G[0]=1;
        G[1]=1;
        for(int i=2; i<=n; i++){
            for(int j=1; j<=i; j++)
                G[i]+= G[j-1]*G[i-j];
        }
        return G[n];
    }
}
