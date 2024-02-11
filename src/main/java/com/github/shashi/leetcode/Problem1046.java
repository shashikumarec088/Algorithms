package com.github.shashi.leetcode;
import java.util.*;
public class Problem1046 {
    public int lastStoneWeight(int[] stones) {
        return lastStoneWeightA1(stones);
    }

    public int lastStoneWeightA2(int[] stones){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->b-a);
        for(int num: stones) pq.add(num);
        while(pq.size()>1){
            int x = pq.poll(), y = pq.poll();
            if(x != y) pq.add(x-y);
        }
        return pq.peek();
    }
    public int lastStoneWeightA1(int[] stones){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);
        for(int weight: stones)pq.add(weight);
        while(pq.size()>1){
            int w1 = pq.poll();
            int w2 = pq.poll();
            if(w1!=w2)pq.add(w1-w2);
        }
        if(pq.isEmpty())return 0;
        return pq.peek();
    }
}