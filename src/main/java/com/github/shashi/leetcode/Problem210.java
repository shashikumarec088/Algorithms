package com.github.shashi.leetcode;
import java.util.*;
public class Problem210 {
    enum State{PROCESSING,PROCESSED}
    boolean cycle;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return findOrderDfs(numCourses,prerequisites);
    }

    public int[] topSortKahns(int n, int[][] pairs){
        List<Integer>[] graph = buildGraph(n, pairs);
        int[] inDegree = inDegrees(graph);
        Queue<Integer> queue = new LinkedList<>();
        for(int i =0; i< n; i++)
            if(inDegree[i]==0)
                queue.add(i);
        int[] out = new int[n];
        int i=0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            out[i++] = node;
            for(int nei : graph[node]){
                inDegree[nei]--;
                if(inDegree[nei]==0)
                    queue.add(nei);
            }
        }
        if(n != i)out = new int[0];
        return out;
    }

    public int[] inDegrees(List<Integer>[] graph){
        int[] inDegree = new int[graph.length];
        for(int i =0; i<graph.length; i++){
            for(int node : graph[i]){
                inDegree[node]++;
            }
        }
        return inDegree;
    }

    public static void main(String[] args) {
        Problem210 problem210 = new Problem210();
        int[][] input = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        problem210.findOrderDfs(4,input);
        LinkedList<Integer> ll = new LinkedList<>();
    }

    public int[] findOrderDfs(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);
        State[] status = new State[numCourses];
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[numCourses];
        for(int i =0; i <numCourses; i++ ){
            if(cycle) break;
            if(status[i]==null)
                dfs(graph,status,stack,i);
        }
        int i=0;
        if(cycle) out = new int[0];
        else{
            while(!stack.isEmpty())
                out[i++] = stack.pop();
        }
        return out;
    }



    public void dfs(List<Integer>[] graph, State[] status,
                    Stack<Integer> stack, int v){
        if(!cycle){
            if(status[v] == State.PROCESSING)
                cycle = true;
            status[v] = State.PROCESSING;
            for(int nei : graph[v])
                if(status[nei] != State.PROCESSED)
                    dfs(graph,status,stack,nei);
            status[v] = State.PROCESSED;
            stack.push(v);
        }
    }

    public List<Integer>[] buildGraph(int n, int[][] pairs){
        List<Integer>[] graph = new List[n];
        for(int i=0; i < n; i++)
            graph[i] = new ArrayList<>();
        for(int[] pair : pairs)
            graph[pair[1]].add(pair[0]);
        return graph;
    }
}
