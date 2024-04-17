package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Problem1306 {
    public boolean canReach(int[] arr, int start) {
        return canReachA4(arr,start);
    }

    public boolean canReachA4(int[] arr, int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            if(arr[node]==0)return true;
            if(arr[node]<0)continue;
            if(node +arr[node] <arr.length)
                queue.offer(node +arr[node]);
            if(node - arr[node] >=0)
                queue.offer(node - arr[node]);
            arr[node]= - arr[node];
        }
        return false;
    }

    /*
        bfs using visited set
    */
    public boolean canReachA3(int[] arr, int start){
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            if(arr[node]==0)return true;
            if(visited.contains(node))continue;
            if(node +arr[node] <arr.length)
                queue.offer(node +arr[node]);
            if(node - arr[node] >=0)
                queue.offer(node - arr[node]);
            visited.add(node);
        }
        return false;
    }

    /*
        since we are asked to find out if we can reach the 0 value
        index by moving either side, it is  a graph problem, we can
        use the array itself to mark the visited as it is garentied
        to provide the +ve integers
    */
    public boolean canReachA2(int[] arr, int s) {
        if(s>=arr.length || s<0 ||arr[s]<0)return false;
        if(arr[s]==0)return true;
        arr[s] = -arr[s];
        boolean ans1 = canReachA2(arr,s+arr[s]);
        if(ans1)return true;
        else return canReachA2(arr,s-arr[s]);
    }

    /*
        since we are asked to find out if we can reach the 0 value
        index by moving either side, it is  a graph problem to mark the
        nodes as visited if we do not mark then we run into stack overflow
        error
    */
    public boolean canReachA1(int[] arr, int s, Set<Integer> visited) {
        if(s>=arr.length || s<0 || visited.contains(s))return false;
        if(arr[s]==0)return true;
        visited.add(s);
        boolean ans1 = canReachA1(arr,s+arr[s],visited);
        if(ans1)return true;
        else return canReachA1(arr,s-arr[s],visited);
    }
}