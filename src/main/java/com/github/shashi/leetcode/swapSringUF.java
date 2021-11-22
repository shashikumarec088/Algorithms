package com.github.shashi.leetcode;
import java.util.*;
class swapStringUF {
    public String smallestStringWithSwaps(String s, int[][] pairs) {
        return smallestUF(s,pairs);
    }

    public static void main(String[] args) {
        swapStringUF uf = new swapStringUF();
        int[][] edges = new int[][]{{0,3},{1,2},{0,2}};
        String input = "dcab";
        String out = uf.smallestStringWithSwaps(input,edges);
        System.out.println(out);

    }
    public String smallestUF(String s,int[][] pairs){
        UnionFindCC uf = new UnionFindCC(s.length());

        for(int[] pair :pairs)
            uf.union(pair[0],pair[1]);

        char[] strChar = s.toCharArray();
        Map<Integer,PriorityQueue<Character>> ccs = new HashMap<>();
        for(int i=0; i<s.length();i++){
            int set = uf.find(i);
            if(!ccs.containsKey(set))
                ccs.put(set,new PriorityQueue<>());
            ccs.get(set).offer(strChar[i]);

        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length();i++){
            sb.append(ccs.get(uf.find(i)).poll());
        }
        return sb.toString();
    }
}
class UnionFindCC{
    private int[] root;
    private int[] rank;

    UnionFindCC(int n){
        root = new int[n];
        rank = new int[n];
        for(int i =0; i<n; i++){
            root[i] = i;
            rank[i]=1;
        }
    }

    public int find(int x){
        if(root[x]==x)return x;
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

    public Map<Integer,List<Integer>> getComponents(){
        Map<Integer,List<Integer>> list = new HashMap<>();
        for(int i=0; i<root.length;i++){
            if(!list.containsKey(root[i]))
                list.put(root[i],new ArrayList<>());
            list.get(root[i]).add(i);
        }
        return list;
    }
}