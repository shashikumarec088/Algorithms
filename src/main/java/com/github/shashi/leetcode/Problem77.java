package com.github.shashi.leetcode;
import java.util.*;
public class Problem77 {
    private int nVal;
    private int kVal;
    private List<List<Integer>> out;

    public static void main(String[] args) {
        Problem77 p77 = new Problem77();
        System.out.println(p77.combine(4,2));
    }
    public List<List<Integer>> combine(int n, int k) {
        return comb(n,k);
    }

    public List<List<Integer>> comb(int n, int k){
        nVal =n;
        kVal = k;
        out = new ArrayList<>();
        backTrack(1,new LinkedList<Integer>());
        return out;
    }

    public void backTrack(int first, LinkedList<Integer> list){
        if(list.size()==kVal){
            out.add(new ArrayList<>(list));
            return;
        }
        for(int i=first; i<nVal+1; i++){
            list.add(i);
            backTrack(i+1,list);
            list.removeLast();
        }
    }
}
