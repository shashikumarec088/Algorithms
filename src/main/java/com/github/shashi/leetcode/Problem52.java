package com.github.shashi.leetcode;

import java.util.*;


public class Problem52 {
    public static void main(String[] args) {
        Problem52 problem119 = new Problem52();
        System.out.println(problem119.totalNQueens(4));
    }

    public int totalNQueens(int n) {
        return totalQns(n);
    }

    public int totalQns(int n){
        Set<Integer> cols = new HashSet<>();
        Set<Integer> ds = new HashSet<>();
        Set<Integer> ads = new HashSet<>();
        return tQs(n,cols,ds,ads,0);
    }

    public int tQs(int n,Set<Integer> cols,
                   Set<Integer> ds,Set<Integer> ads,int r){

        if(r==n) return 1;
        int solns = 0;
        for(int c=0; c<n;c++){
            int d = r-c;
            int ad = r+c;
            if(cols.contains(c) || ds.contains(d)||
                    ads.contains(ad)) continue;
            cols.add(c);
            ds.add(d);
            ads.add(ad);
            solns += tQs(n,cols,ds,ads,r+1);
            cols.remove(c);
            ds.remove(d);
            ads.remove(ad);
        }
        return solns;
    }
}
