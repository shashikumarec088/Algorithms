package com.github.shashi.leetcode;


import java.util.*;

class Problem797 {
    private int target;
    private int[][] graph;
    private Map<Integer,List<List<Integer>>> memo;

    public List<List<Integer>> getAllPathsDP(int[][] graphs){
        graph = graphs;
        target = graph.length-1;
        memo = new HashMap<>();
        return dp(0);
    }

    public List<List<Integer>> paths(int[][] graph){
        int n = graph.length;
        List<List<Integer>> out = new ArrayList<>();
        Queue<LinkedList<Integer>> queue = new LinkedList<>();
        LinkedList<Integer> src = new LinkedList<>();
        src.add(0);
        queue.add(src);
        while(!queue.isEmpty()){
            LinkedList<Integer> list = queue.poll();
            if(list.peekLast() == n-1)
                out.add(list);
            else{
                for(int nei : graph[list.peekLast()]){
                    LinkedList<Integer> nList = new LinkedList<>(list);
                    nList.add(nei);
                    queue.add(nList);
                }
            }
        }

        return out;
    }

    public List<List<Integer>> dp(int start){
        if(memo.containsKey(start)) return memo.get(start);
        List<List<Integer>> result = new ArrayList<>();
        if(start == target){
            List<Integer> current = new ArrayList<>();
            current.add(start);
            result.add(current);
        }else{
            for(int nei : graph[start]){
                for(List<Integer> path :dp(nei)){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.addAll(path);
                    result.add(list);
                }
            }
        }
        memo.put(start,result);
        return result;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return paths(graph);
    }

    public List<List<Integer>> allPathsDfs(int[][] graph){
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> current = new LinkedList<>();
        if(graph == null || graph.length==0)return result;
        int start=0, end = graph.length-1;
        current.add(start);
        dfs(graph,result,current,start,end);
        return result;
    }

    public void dfs(int[][] graph,List<List<Integer>> result,LinkedList<Integer> current,
                    int start, int end){
        if(start== end)
            result.add(new ArrayList<>(current));
        else{
            for(int i : graph[start]){
                current.add(i);
                dfs(graph,result,current,i,end);
                current.removeLast();
            }
        }
    }
}